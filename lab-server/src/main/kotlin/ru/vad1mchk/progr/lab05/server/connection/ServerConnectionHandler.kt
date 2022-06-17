package ru.vad1mchk.progr.lab05.server.connection

import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.server.commander.CommandInvoker
import ru.vad1mchk.progr.lab05.server.communication.RequestExecutor
import ru.vad1mchk.progr.lab05.server.communication.RequestReceiver
import ru.vad1mchk.progr.lab05.server.communication.ResponseSender
import java.io.IOException
import java.net.InetSocketAddress
import java.net.SocketAddress
import java.nio.ByteBuffer
import java.nio.channels.SelectionKey
import java.nio.channels.Selector
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Class that is responsible for maintaining server's connection with the clients.
 * @constructor Constructs a new [ServerConnectionHandler] with the specified port number.
 * @param port Port number of the server.
 */
class ServerConnectionHandler(
    port: Int,
    private val printer: Printer,
    private val commandInvoker: CommandInvoker
) {
    companion object {
        const val SELECTION_DELAY = 1000L
        const val DEFAULT_BUFFER_SIZE = 1024
    }

    private var serverChannel: ServerSocketChannel = ServerSocketChannel.open()
    private val cachedThreadPool: ExecutorService = Executors.newCachedThreadPool()
    private val fixedThreadPool: ExecutorService = Executors.newFixedThreadPool(
        Runtime.getRuntime().availableProcessors()
    )
    private val selector: Selector = Selector.open()
    private val channels = HashMap<SocketChannel, ByteBuffer?>()
    private val channelStates = HashMap<SocketChannel, ChannelState>()

    init {
        serverChannel.bind(InetSocketAddress(port))
        printer.printNewLine("Сервер использует порт ${serverChannel.socket().localPort}.")
    }

    fun run() {
        try {
            serverChannel.apply {
                configureBlocking(false)
                register(selector, SelectionKey.OP_ACCEPT)
            }
            listen()
        } catch (e: IOException) {
            printer.printNewLine("Во время работы сервера произошла ошибка ввода-вывода.")
            printer.printNewLine("Закрытие сервера...")
        }
    }

    fun close() {
        try {
            selector.close()
            serverChannel.close()
        } catch (e: IOException) {
            printer.printNewLine("Во время закрытия сервера произошла ошибка ввода-вывода.")
        }
    }

    private fun listen() {
        while (true) {
            var selectionKey: SelectionKey? = null
            try {
                val keyCount = selector.select(SELECTION_DELAY)
                if (keyCount != 0) {
                    val selectedKeys = selector.selectedKeys().iterator()
                    while (selectedKeys.hasNext()) {
                        selectionKey = selectedKeys.next()
                        selectedKeys.remove()
                        if (selectionKey.isValid) {
                            if (selectionKey.isAcceptable) {
                                accept()
                            } else if (selectionKey.isReadable) {
                                read(selectionKey)
                            } else if (selectionKey.isWritable) {
                                write(selectionKey)
                            }
                        }
                    }
                }
            } catch (e: IOException) {
                if (selectionKey != null) {
                    kill(selectionKey.channel() as SocketChannel)
                }
            }
        }
    }

    private fun accept() {
        val channel = serverChannel.accept()
        val address = channel.remoteAddress
        channel.configureBlocking(false)
        channel.register(selector, SelectionKey.OP_READ)
        channels[channel] = ByteBuffer.allocate(0)
        channelStates[channel] = ChannelState.READY_TO_READ
    }

    private fun read(selectionKey: SelectionKey) {
        val channel = selectionKey.channel() as SocketChannel
        if (channelStates[channel] == ChannelState.READY_TO_READ) {
            channelStates[channel] = ChannelState.READING
            val requestReceiver = RequestReceiver(channel, channels, selector, channelStates)
            CompletableFuture
                .supplyAsync(requestReceiver::receive, cachedThreadPool)
                .thenAcceptAsync { request ->
                    Thread(
                        RequestExecutor(
                            request,
                            commandInvoker,
                            channel,
                            channels,
                            selector,
                            channelStates
                        )
                    ).let {
                        it.start()
                    }
                }
        }
        if (channels[channel] == null) {
            throw IOException()
        }
    }

    private fun write(selectionKey: SelectionKey) {
        val channel = selectionKey.channel() as SocketChannel
        if (channelStates[channel] == ChannelState.READY_TO_WRITE) {
            channelStates[channel] = ChannelState.WRITING
            val responseSender = ResponseSender(channel, channels, selector, channelStates)
            fixedThreadPool.execute(responseSender::send)
        }
        if (channels[channel] == null) {
            throw IOException()
        }
    }

    private fun kill(socketChannel: SocketChannel): SocketAddress {
        val address = socketChannel.remoteAddress
        channels.remove(socketChannel)
        socketChannel.close()
        return address
    }
}