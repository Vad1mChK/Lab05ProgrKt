package ru.vad1mchk.progr.lab05.client.application

import ru.vad1mchk.progr.lab05.common.communication.Request
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.BufferedReader
import java.io.Closeable
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.PrintWriter
import java.net.InetAddress
import java.net.Socket

/**
 * Application class used only by the client and called from the
 * client's main entry point.
 */
class ClientApplication {
    lateinit var clientSocket: Socket
    lateinit var input: ObjectInputStream
    lateinit var output: ObjectOutputStream

    fun launch() {
        startConnection(InetAddress.getByName("127.0.0.1"), 8080)
    }

    fun startConnection(ipAddress: InetAddress, port: Int) {
        println("Привет, я -- клиент")
        clientSocket = Socket(ipAddress, port)
        input = ObjectInputStream(clientSocket.getInputStream())
        output = ObjectOutputStream(clientSocket.getOutputStream())
        mainLoop()
    }

    fun mainLoop() {
        while (!clientSocket.isClosed) {
            val line = readLine()
            if (line.isNullOrBlank()) break
            sendRequest(Request(line))
        }
        stopConnection()
    }

    fun sendRequest(request: Request) {
        output.writeObject(request)
        output.flush()
    }

    fun stopConnection() {
        println("Всё, я спать...")
        for (closeable in arrayOf<Closeable>(
            input, output, clientSocket
        )) {
            closeable.close()
        }
    }
}