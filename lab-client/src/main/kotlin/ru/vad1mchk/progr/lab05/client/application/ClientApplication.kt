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
import java.net.UnknownHostException
import java.util.*
import kotlin.NoSuchElementException
import kotlin.system.exitProcess

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

    private fun mainLoop() {
        while (isWorking) {
            val enteredCommand = commandListener.readCommand()
            if (enteredCommand != null) {
                if ("exit" == enteredCommand.name.lowercase()) {
                    if (enteredCommand.arguments.isEmpty()) {
                        Printer.printNewLine("Завершение работы.")
                        exitProcess(0)
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

    private fun listen(request: Request?): Response? {
        if (!connectionHandler.isOpen) {
            connectionHandler.reopenConnection()
        }
        if (connectionHandler.isOpen) {
            try {
                if (request != null) connectionHandler.send(request)
                return connectionHandler.receive(connectionHandler.socket.receiveBufferSize)
            } catch (e: IOException) {
                e.printStackTrace()
                connectionHandler.close()
            }
        }
        return null
    }

    private fun executeScript(filePath: String) {
        try {
            val scriptFileReader = ScriptFileReader(filePath)
            for (request in scriptFileReader.readAll()) {
                connectionHandler.send(request)
            }
        } catch (e: FileException) {
            Printer.printError(e)
        }
    }
}