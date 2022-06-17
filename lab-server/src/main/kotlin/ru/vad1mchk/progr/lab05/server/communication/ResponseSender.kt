package ru.vad1mchk.progr.lab05.server.communication

import ru.vad1mchk.progr.lab05.common.communication.Serializer
import ru.vad1mchk.progr.lab05.server.connection.ChannelState
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.channels.SelectionKey
import java.nio.channels.Selector
import java.nio.channels.SocketChannel

class ResponseSender(
    val channel: SocketChannel,
    val channels: MutableMap<SocketChannel, ByteBuffer?>,
    val selector: Selector,
    val channelStates: MutableMap<SocketChannel, ChannelState>
) {
    private companion object {
        const val DEFAULT_BUFFER_SIZE = 4096
    }

    private val serializer = Serializer()

    fun send() {
        try {
            val buffer = channels[channel]
            var responseLength = 0
            var bytesWritten = channel.write(buffer)
            responseLength += bytesWritten
            while (buffer!!.hasRemaining()) {
                bytesWritten = channel.write(buffer)
                responseLength += bytesWritten
            }
            channels[channel] = ByteBuffer.allocate(0)
            channelStates[channel] = ChannelState.READY_TO_READ
            channel.register(selector, SelectionKey.OP_READ)
        } catch (e: IOException) {
            channels[channel] = null
        }
    }
}