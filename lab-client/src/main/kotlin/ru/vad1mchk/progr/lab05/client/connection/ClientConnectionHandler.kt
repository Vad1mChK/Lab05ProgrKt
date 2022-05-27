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
        private const val RESPONSE_TIMEOUT = 10000
    }
    lateinit var socket: Socket
    lateinit var inputStream: InputStream
    lateinit var outputStream: OutputStream
    lateinit var lastAddress: InetAddress
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
                lastAddress = socket.inetAddress
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

    fun reopenConnection() {
        openConnection(lastAddress, lastPort)
    }

    fun send(request: Request) {
        val bytes = Serializer.serialize(request)
        if (bytes != null) {
            outputStream.write(bytes)
            outputStream.flush()
        }
    }

    fun receive(bufferSize: Int): Response? {
        var mainBuffer = ByteBuffer.allocate(0)
        var bytesToDeserialize: ByteArray
        val bis = BufferedInputStream(inputStream)
        bytesToDeserialize = ByteArray(bufferSize)
        val bytesCount = bis.read(bytesToDeserialize)
        val newBuffer = ByteBuffer.allocate(mainBuffer.capacity() + bytesCount)
        newBuffer.put(mainBuffer)
        newBuffer.put(ByteBuffer.wrap(bytesToDeserialize, 0, bytesCount))
        mainBuffer = ByteBuffer.wrap(newBuffer.array())
        return Serializer.deserialize(mainBuffer.array()) as Response?
    }

    fun close() {
        inputStream.close()
        outputStream.close()
        socket.close()
        isOpen = false
    }
}