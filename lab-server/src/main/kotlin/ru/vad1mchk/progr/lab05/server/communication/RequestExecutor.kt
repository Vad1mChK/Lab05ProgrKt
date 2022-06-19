package ru.vad1mchk.progr.lab05.server.communication

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Serializer
import ru.vad1mchk.progr.lab05.server.commander.CommandInvoker
import ru.vad1mchk.progr.lab05.server.connection.ChannelState
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

    override fun run() {
        if (request != null) {
            channels[channel] = ByteBuffer.wrap(serializer.serialize(commandInvoker.executeRequest(request)))
            channelStates[channel] = ChannelState.READY_TO_WRITE
            channel.register(selector, SelectionKey.OP_WRITE)
        }
    }
}