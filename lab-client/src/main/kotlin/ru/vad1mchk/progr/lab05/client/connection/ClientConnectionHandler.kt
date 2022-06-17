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

/**
 * Class that is responsible for maintaining client's connection to the server.
 */
class ClientConnectionHandler(private val printer: Printer) {
    companion object {
        private const val RESPONSE_TIMEOUT = 10000
    }

    lateinit var socket: Socket
    lateinit var inputStream: InputStream
    lateinit var outputStream: OutputStream
    lateinit var lastAddress: InetAddress
    private val serializer = Serializer()
    var lastPort: Int = 0
    var isOpen = false
        private set

    /**
     * Opens the connection to the server via socket with specified address and port.
     * @param address IP address to use.
     * @param port Port to use.
     */
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
                printer.printNewLine("Подключение установлено.")
                break
            } catch (e: IOException) {
                printer.printError("Подключение к серверу не было установлено.")
                printer.printNewLine("Для переподключения с теми же адресом и номером порта нажмите ENTER.")
                readLine()
            }
        }
    }

    /**
     * Reopens the connection if it was closed. Uses the same port and address.
     */
    fun reopenConnection() {
        openConnection(lastAddress, lastPort)
    }

    /**
     * Sends this request to the server.
     * @param request Request to send.
     */
    fun send(request: Request) {
        val bytes = serializer.serialize(request)
        if (bytes != null) {
            outputStream.write(bytes)
            outputStream.flush()
        }
    }

    /**
     * Receives this response from the server.
     * @param bufferSize Size of buffer to use.
     * @return The received response.
     */
    fun receive(bufferSize: Int): Response? {
        val bytesToDeserialize = ByteArray(bufferSize)
        val bufferedInputStream = BufferedInputStream(inputStream)
        val bytesCount = bufferedInputStream.read(bytesToDeserialize)
        return serializer.deserialize(bytesToDeserialize) as Response?
    }

    /**
     * Closes this connection. It can be reopened later by calling [reopenConnection].
     */
    fun close() {
        inputStream.close()
        outputStream.close()
        socket.close()
        isOpen = false
    }
}