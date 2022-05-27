package ru.vad1mchk.progr.lab05.client.connection

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.communication.Serializer
import ru.vad1mchk.progr.lab05.common.io.Printer
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.InetAddress
import java.net.Socket
import java.nio.ByteBuffer


class ClientConnectionHandler {
    companion object {
        private const val RESPONSE_TIMEOUT = 5000
    }
    lateinit var socket: Socket
    lateinit var inputStream: InputStream
    lateinit var outputStream: OutputStream
    lateinit var lastAddress: String
    var lastPort: Int = 0
    var isOpen = false
        private set

    fun openConnection(address: InetAddress, port: Int) {
        while (true) {
            try {
                socket = Socket(address, port)
                socket.soTimeout = RESPONSE_TIMEOUT
                inputStream = socket.getInputStream()
                outputStream = socket.getOutputStream()
                lastAddress = socket.remoteSocketAddress.toString()
                lastPort = socket.port
                isOpen = true
                Printer.printNewLine("Подключение установлено.")
                break
            } catch (e: IOException) {
                Printer.printError("Подключение к серверу не было установлено.")
                Printer.printNewLine("Для переподключения с теми же адресом и номером порта нажмите ENTER.")
                readLine()
            }
        }
    }

    fun send(request: Request) {
        val bytes = Serializer.serialize(request)
        if (bytes != null) {
            outputStream.apply {
                write(bytes)
                flush()
                close()
            }
        }
    }

    fun receive(): Response {
        socket.soTimeout = RESPONSE_TIMEOUT
        val bufferSize = socket.receiveBufferSize
        var mainBuffer: ByteBuffer = ByteBuffer.allocate(0)
        while (true) {
            val bytesToDeserialize = ByteArray(bufferSize)
            val bufferedInputStream = BufferedInputStream(inputStream)
            val bytesCount = inputStream.read(bytesToDeserialize)
            var newBuffer= ByteBuffer.allocate(mainBuffer.capacity() + bytesCount)
            newBuffer.put(mainBuffer)
            newBuffer.put(ByteBuffer.wrap(bytesToDeserialize, 0, bytesCount))
            mainBuffer = ByteBuffer.wrap(newBuffer.array())
            var response: Response? = Serializer.deserialize(mainBuffer.array()) as Response?
            if (response == null) {
                val buffers = ArrayList<ByteBuffer>()
                var bytesLeft = bufferedInputStream.available()
                var len = bytesLeft
                while (bytesLeft > 0) {
                    val bytesLeftToSerialize = ByteArray(bytesLeft)
                    bufferedInputStream.read(bytesLeftToSerialize)
                    buffers.add(ByteBuffer.wrap(bytesLeftToSerialize))
                    bytesLeft = bufferedInputStream.available()
                    len += bytesLeft
                }
                newBuffer = ByteBuffer.allocate(len + mainBuffer.capacity())
                newBuffer.put(mainBuffer)
                buffers.forEach(newBuffer::put)
                mainBuffer = ByteBuffer.wrap(newBuffer.array())
                response = Serializer.deserialize(mainBuffer.array()) as Response?
            }
            if (response != null) {
                return response
            }
        }
    }

    fun close() {
        for (closeable in arrayOf(inputStream, outputStream, socket)) {
            closeable.close()
        }
        isOpen = false
    }
}