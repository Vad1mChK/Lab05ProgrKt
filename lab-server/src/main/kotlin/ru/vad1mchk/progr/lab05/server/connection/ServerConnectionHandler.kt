package ru.vad1mchk.progr.lab05.server.connection

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Serializer
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.server.commander.CommandInvoker
import ru.vad1mchk.progr.lab05.server.util.Configuration
import java.io.IOException
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket
import java.net.SocketAddress
import java.nio.ByteBuffer
import java.nio.channels.SelectionKey
import java.nio.channels.Selector
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel
import javax.xml.bind.annotation.XmlType.DEFAULT

class ServerConnectionHandler(port: Int): Runnable {
    companion object {
        const val SELECTION_DELAY = 1000L
        const val DEFAULT_BUFFER_SIZE = 1024
    }

    var serverChannel: ServerSocketChannel = ServerSocketChannel.open()
    val selector: Selector = Selector.open()
    val channels = HashMap<SocketChannel, ByteBuffer>()

    init {
        serverChannel.bind(InetSocketAddress(port))
        Printer.printNewLine("Сервер использует порт ${serverChannel.socket().localPort}.")
    }

    override fun run() {
        try {
            serverChannel.apply {
                configureBlocking(false)
                register(selector, SelectionKey.OP_ACCEPT)
            }
            listen()
        } catch (e: IOException) {
            Printer.printNewLine("Во время работы сервера произошла ошибка ввода-вывода.")
            Printer.printNewLine("Закрытие сервера...")
        }
    }

    fun close() {
        try {
            selector.close()
            serverChannel.close()
        } catch (e: IOException) {
            Printer.printNewLine("Во время закрытия сервера произошла ошибка ввода-вывода.")
        }
    }

    fun listen() {
        while (Configuration.isWorking) {
            var selectionKey: SelectionKey? = null
            try {
                val keyCount = selector.select(SELECTION_DELAY)
                if (keyCount != 0) {
                    val selectedKeys = selector.selectedKeys().iterator()
                    while(selectedKeys.hasNext()) {
                        selectionKey = selectedKeys.next()
                        selectedKeys.remove()
                        if (selectionKey.isValid) {
                            if (selectionKey.isAcceptable) {
                                accept(selectionKey)
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

    private fun accept(selectionKey: SelectionKey): SocketAddress {
        val channel = serverChannel.accept()
        val address = channel.remoteAddress
        channel.configureBlocking(false)
        channel.register(selector, SelectionKey.OP_READ)
        channels[channel] = ByteBuffer.allocate(0)
        return address
    }

    private fun read(selectionKey: SelectionKey): Request? {
        val channel = selectionKey.channel() as SocketChannel
        val buffer = ByteBuffer.allocate(DEFAULT_BUFFER_SIZE)
        var bytesRead = channel.read(buffer)
        if (bytesRead == -1) {
            kill(channel)
            return null
        }
        val newBuffer = ByteBuffer.allocate(channels[channel]!!.capacity() + bytesRead)
        newBuffer.put(channels[channel]!!.array())
        newBuffer.put(ByteBuffer.wrap(buffer.array(), 0, bytesRead))
        channels[channel] = newBuffer
        val request = Serializer.deserialize(channels[channel]!!.array()) as Request?
        if (request != null) {
            channels[channel] = ByteBuffer.wrap(
                Serializer.serialize(Configuration.COMMAND_INVOKER.executeRequest(request))
            )
            channel.register(selector, SelectionKey.OP_WRITE)
        }
        return request
    }

    private fun write(selectionKey: SelectionKey): Int {
        val channel = selectionKey.channel() as SocketChannel
        val buffer = ByteBuffer.allocate(DEFAULT_BUFFER_SIZE)
        var responseLength = 0
        var bytesWritten = channel.write(buffer)
        responseLength += bytesWritten
        while (buffer.hasRemaining()) {
            bytesWritten = channel.write(buffer)
            responseLength += bytesWritten
        }
        channels[channel] = ByteBuffer.allocate(0)
        channel.register(selector, SelectionKey.OP_READ)
        return responseLength
    }

    private fun kill(socketChannel: SocketChannel): SocketAddress {
        val address = socketChannel.remoteAddress
        channels.remove(socketChannel)
        socketChannel.close()
        return address
    }
}