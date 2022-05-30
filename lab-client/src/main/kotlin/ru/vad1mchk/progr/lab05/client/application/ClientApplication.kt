package ru.vad1mchk.progr.lab05.client.application

import ru.vad1mchk.progr.lab05.client.connection.ClientConnectionHandler
import ru.vad1mchk.progr.lab05.common.application.AbstractApplication
import ru.vad1mchk.progr.lab05.common.communication.EnteredCommand
import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.RequestCreator
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.exceptions.FileException
import ru.vad1mchk.progr.lab05.common.file.FileManager
import ru.vad1mchk.progr.lab05.common.io.CommandListener
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.common.io.ScriptFileReader
import java.io.FileInputStream
import java.io.IOException
import java.net.InetAddress
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.*
import kotlin.NoSuchElementException
import kotlin.system.exitProcess

/**
 * Implementation of [AbstractApplication] used solely by client.
 */
class ClientApplication: AbstractApplication() {
    private val connectionHandler = ClientConnectionHandler()
    private var commandListener = CommandListener(System.`in`, false, "1337h4x0r")
    private var isWorking = true
    override var scanner = Scanner(System.`in`)
    private val requestCreator = RequestCreator()

    override fun launch(args: Array<String>) {
        Printer.printNewLine("""
            |Клиент менеджера космических десантников приветствует вас.
            |Данное приложение работает только при наличии соединения с сервером.
            |Для продолжения введите адрес и порт сервера в локальной сети. 
            """.trimMargin())
        connectionHandler.openConnection(readInetAddress(), readPort())
        mainLoop()
    }

    /**
     * Main loop of the program, in which the command requests are sent and responses received.
     */
    private fun mainLoop() {
        while (isWorking) {
            val enteredCommand = commandListener.readCommand()
            if (enteredCommand != null) {
                if ("exit" == enteredCommand.name.lowercase()) {
                    if (enteredCommand.arguments.isEmpty()) {
                        Printer.printNewLine("Завершение работы.")
                        isWorking = false
                    } else {
                        Printer.printError("Для того чтобы выйти из приложения, введите команду exit без аргументов.")
                    }
                } else if ("execute_script" == enteredCommand.name.lowercase()) {
                    if (enteredCommand.arguments.size == 1) {
                        executeScript(enteredCommand.arguments[0])
                    } else {
                        Printer.printError("Неверное количество аргументов команды.")
                    }
                }
                listen(requestCreator.requestFromEnteredCommand(enteredCommand))?.also {
                    println(it.stringMessage)
                    it.spaceMarines?.stream()?.forEach{ marine -> Printer.printNewLine(marine.toString()) }
                }
            }
        }
    }

    /**
     * Reads IP address of the server from the standard input, looping until a valid address that can be found is
     * entered.
     * @return The entered IP address.
     */
    private fun readInetAddress(): InetAddress {
        while (true) {
            Printer.printNewLine("Введите IP-адрес сервера: ")
            try {
                return InetAddress.getByName(scanner.nextLine())
            } catch (e: UnknownHostException) {
                Printer.printError("Адрес не найден в сети.")
            } catch (e: NoSuchElementException) {
                exitProcess(0)
            }
        }
    }

    /**
     * Listens to the connection, receiving incoming responses from the server.
     * @param request Request to send.
     * @return The server's response to the sent request.
     */
    private fun listen(request: Request?): Response? {
        if (!connectionHandler.isOpen) {
            connectionHandler.reopenConnection()
        }
        if (connectionHandler.isOpen) {
            try {
                if (request != null) connectionHandler.send(request)
                return connectionHandler.receive(connectionHandler.socket.receiveBufferSize)
            } catch (e: SocketTimeoutException) {
                Printer.printError("Время ожидания ответа от сервера истекло.")
            } catch (e: SocketException) {
                Printer.printError("Соединение с сервером было разорвано.")
                connectionHandler.close()
            } catch (e: IOException) {
                Printer.printError("Во время обмена информацией с сервером произошла ошибка ввода-вывода.")
                connectionHandler.close()
            }
        }
        return null
    }

    /**
     * Executes the specified script file, sending commands to the server one by one.
     * @param filePath Path of script file to execute.
     */
    private fun executeScript(filePath: String) {
        try {
            val scriptFileReader = ScriptFileReader(filePath)
            scriptFileReader.readAll().forEach { connectionHandler.send(it) }
        } catch (e: FileException) {
            Printer.printError(e)
        }
    }
}