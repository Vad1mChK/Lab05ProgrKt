package ru.vad1mchk.progr.lab05.server.communication

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Serializer
import ru.vad1mchk.progr.lab05.server.commander.CommandInvoker
import ru.vad1mchk.progr.lab05.server.connection.ChannelState
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.channels.SelectionKey
import java.nio.channels.Selector
import java.nio.channels.SocketChannel

class RequestExecutor(
    val request: Request?,
    val commandInvoker: CommandInvoker,
    val channel: SocketChannel,
    val channels: MutableMap<SocketChannel, ByteBuffer?>,
    val selector: Selector,
    val channelStates: MutableMap<SocketChannel, ChannelState>
) : Runnable {
    private val serializer = Serializer()
    private val responseSender = ResponseSender(channel, channels, selector, channelStates)

    override fun run() {
        try {
            if (request != null) {
                val response = commandInvoker.executeRequest(request)
                if (response?.notification == true) {
                    channels.forEach{
                        if (channel != it.key) {
                            channels[it.key] = ByteBuffer.wrap(serializer.serialize(response))
                            channelStates[it.key] = ChannelState.READY_TO_WRITE
                            it.key.register(selector, SelectionKey.OP_WRITE)

                            val buffer = channels[it.key]
                            var responseLength = 0
                            var bytesWritten = it.key.write(buffer)
                            responseLength += bytesWritten
                            while (buffer!!.hasRemaining()) {
                                bytesWritten = it.key.write(buffer)
                                responseLength += bytesWritten
                            }
                            channels[it.key] = ByteBuffer.allocate(0)
                            channelStates[it.key] = ChannelState.READY_TO_READ
                            it.key.register(selector, SelectionKey.OP_READ)
                        }
                    }
                }
                channels[channel] = ByteBuffer.wrap(serializer.serialize(response))
                channelStates[channel] = ChannelState.READY_TO_WRITE
                channel.register(selector, SelectionKey.OP_WRITE)
            }
        } catch (e: IOException) {
            channels[channel] = null
        }
    }
}