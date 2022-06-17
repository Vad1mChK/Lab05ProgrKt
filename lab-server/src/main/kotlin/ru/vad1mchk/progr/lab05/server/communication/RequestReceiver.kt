package ru.vad1mchk.progr.lab05.server.communication

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Serializer
import ru.vad1mchk.progr.lab05.server.connection.ChannelState
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.channels.Selector
import java.nio.channels.SocketChannel

class RequestReceiver(
    private val channel: SocketChannel,
    private val channels: MutableMap<SocketChannel, ByteBuffer?>,
    private val selector: Selector,
    private val channelStates: MutableMap<SocketChannel, ChannelState>
) {
    private companion object {
        const val DEFAULT_BUFFER_SIZE = 4096
    }

    private val serializer = Serializer()

    fun receive(): Request? {
        try {
            val buffer = ByteBuffer.allocate(DEFAULT_BUFFER_SIZE)
            val bytesRead = channel.read(buffer)
            if (bytesRead == -1) {
                channels.remove(channel)
                channel.close()
                return null
            }
            val newBuffer = ByteBuffer.allocate(channels[channel]!!.capacity() + bytesRead)
            newBuffer.put(channels[channel]!!.array())
            newBuffer.put(ByteBuffer.wrap(buffer.array(), 0, bytesRead))
            channels[channel] = newBuffer
            return serializer.deserialize(channels[channel]!!.array()) as Request?
        } catch (e: IOException) {
            channels[channel] = null
        }
        return null
    }
}