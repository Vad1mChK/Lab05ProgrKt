package ru.vad1mchk.progr.lab05.server.application

import ru.vad1mchk.progr.lab05.common.communication.Request
import java.io.*
import java.net.ServerSocket
import java.net.Socket

/**
 * Application class used only by the server and called from the
 * server's main entry point.
 */
class ServerApplication {
    private lateinit var serverSocket: ServerSocket
    private lateinit var clientSocket: Socket
    private lateinit var input: ObjectInputStream
    private lateinit var output: ObjectOutputStream
    fun launch() {
        start(8080)
    }

    fun start(port: Int) {
        println("Привет, я -- сервак")
        serverSocket = ServerSocket(port)
        clientSocket = serverSocket.accept()
        println("Кто-то подключился")
        input = ObjectInputStream(clientSocket.getInputStream())
        output = ObjectOutputStream(clientSocket.getOutputStream())
        mainLoop()
    }

    fun mainLoop() {
        var objectBeingRead: Any? = null
        while (input.readObject().also {objectBeingRead = it} != null) {
            val request = objectBeingRead as Request
            println(request.stringMessage)
        }
        stop()
    }

    fun stop() {
        println("Всё, я спать...")
        for (closeable in arrayOf<Closeable>(
            input, output, serverSocket, clientSocket
        )) {
            closeable.close()
        }
    }
}