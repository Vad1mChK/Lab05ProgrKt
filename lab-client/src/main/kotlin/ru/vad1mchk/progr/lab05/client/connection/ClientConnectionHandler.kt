package ru.vad1mchk.progr.lab05.client.connection

import ru.vad1mchk.progr.lab05.client.util.Configuration
import ru.vad1mchk.progr.lab05.common.connection.Request
import ru.vad1mchk.progr.lab05.common.connection.Response
import ru.vad1mchk.progr.lab05.common.connection.Serializer
import ru.vad1mchk.progr.lab05.common.io.OutputManager
import java.io.BufferedInputStream
import java.io.Closeable
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.InetAddress
import java.net.ServerSocket
import java.net.Socket
import java.net.UnknownHostException
import java.nio.ByteBuffer
import kotlin.properties.Delegates

/**
 * Class that controls connection to the server from the client's side.
 */
class ClientConnectionHandler {
    companion object {
        val stringResources = Configuration.STRING_RESOURCES
    }
    private lateinit var socket: Socket
    private var isOpen = false
    private lateinit var inputStream: InputStream
    private lateinit var outputStream: OutputStream
    private var lastAddress: String? = null
    private var lastPort: Int = 0

    fun openConnection(inetAddress: InetAddress, serverPort: Int) {
        try {
            socket = Socket(inetAddress, serverPort)
            isOpen = true
            inputStream = socket.getInputStream()
            outputStream = socket.getOutputStream()
            lastAddress = socket.remoteSocketAddress.toString()
            lastPort = socket.port
            OutputManager.sayInfo(stringResources.getString("connection success"))
        } catch (e: IOException) {
            isOpen = false
            OutputManager.sayException(stringResources.getString("IOException cannotConnectToServer"))
        }
    }

    /**
     * Serializes the request and sends it to the server.
     * @param request Request to send.
     */
    fun sendRequest(request: Request) {
        if (isOpen) {
            request.clientInformation = "${InetAddress.getLocalHost()}:${socket.localPort}"
            val bufferToSend = Serializer.serializeRequest(request).array()
            outputStream.write(bufferToSend)
            outputStream.flush()
        } else {
            OutputManager.sayException(stringResources.getString("exception cannotSendConnectionClosed"))
        }
    }

    /**
     * Receives the response from the server and deserializes it.
     * @return Response received.
     */
    fun receiveResponse(): Response {
        var mainBuffer = ByteBuffer.allocate(0)
        while (true) {
            val bytesToDeserialize = ByteArray(socket.receiveBufferSize)
            val bufferedInputStream = BufferedInputStream(inputStream)
            val bytesCount = bufferedInputStream.read(bytesToDeserialize)
            var newBuffer = ByteBuffer.allocate(mainBuffer.capacity()+bytesCount)
            newBuffer.put(mainBuffer)
            newBuffer.put(ByteBuffer.wrap(bytesToDeserialize, 0, bytesCount))
            mainBuffer = ByteBuffer.wrap(newBuffer.array())
            var response = Serializer.deserializeResponse(mainBuffer.array())
            if (response == null) {
                val byteBuffers = ArrayList<ByteBuffer>()
                var bytesLeft = bufferedInputStream.available()
                var length = bytesLeft
                while (bytesLeft > 0) {
                    val bytesLeftToDeserialize = ByteArray(bytesLeft)
                    bufferedInputStream.read(bytesLeftToDeserialize)
                    byteBuffers.add(ByteBuffer.wrap(bytesLeftToDeserialize))
                    bytesLeft = bufferedInputStream.available()
                    length += bytesLeft
                }
                newBuffer = ByteBuffer.allocate(mainBuffer.capacity()+length)
                newBuffer.put(mainBuffer)
                byteBuffers.forEach{ newBuffer.put(it) }
                mainBuffer = ByteBuffer.wrap(newBuffer.array())
                response = Serializer.deserializeResponse(mainBuffer.array())
            }
            if (response != null) {
                return response
            }
        }
    }

    fun close() {
        for (closeable in arrayOf<Closeable>(inputStream, outputStream, socket)) {
            closeable.close()
        }
        isOpen = false
    }
}