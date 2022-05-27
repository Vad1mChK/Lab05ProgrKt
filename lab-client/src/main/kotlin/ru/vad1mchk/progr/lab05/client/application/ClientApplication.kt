package ru.vad1mchk.progr.lab05.client.application

import ru.vad1mchk.progr.lab05.client.connection.ClientConnectionHandler
import ru.vad1mchk.progr.lab05.common.application.AbstractApplication
import ru.vad1mchk.progr.lab05.common.communication.EnteredCommand
import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.RequestCreator
import ru.vad1mchk.progr.lab05.common.exceptions.FileException
import ru.vad1mchk.progr.lab05.common.file.FileManager
import ru.vad1mchk.progr.lab05.common.io.CommandListener
import ru.vad1mchk.progr.lab05.common.io.Printer
import java.io.FileInputStream
import java.io.IOException
import java.net.InetAddress
import java.net.UnknownHostException
import kotlin.system.exitProcess

class ClientApplication: AbstractApplication() {
    private val connectionHandler = ClientConnectionHandler()
    private var commandListener = CommandListener(System.`in`, false, "1337h4x0r")
    private var isWorking = true

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
                formAndSendRequest(enteredCommand)?.let { receiveAndPrintResponse(it) }
            }
        }
    }

    private fun readInetAddress(): InetAddress {
        while (true) {
            Printer.printNoNewLine("Введите IP-адрес сервера: ")
            try {
                return InetAddress.getByName(scanner.nextLine())
            } catch (e: UnknownHostException) {
                Printer.printError("Адрес не найден в сети.")
            } catch (e: NoSuchElementException) {
                exitProcess(0)
            }
        }
    }

    private fun formAndSendRequest(enteredCommand: EnteredCommand): Request? {
        val requestCreator = RequestCreator()
        val request = requestCreator.requestFromEnteredCommand(enteredCommand)
        if (request != null) {
            if (!connectionHandler.isOpen) {
                connectionHandler.openConnection(readInetAddress(), readPort())
            }
            connectionHandler.sendRequest(request)
        }
        return request
    }

    private fun receiveAndPrintResponse(request: Request) {
        if (!connectionHandler.isOpen) {
            connectionHandler.openConnection(readInetAddress(), readPort())
        }
        if (connectionHandler.isOpen) {
            try {
                connectionHandler.sendRequest(request)
                val response = connectionHandler.receive()
                response.stringMessage.also { if(it.isNotEmpty()) Printer.printNewLine(it) }
                response.spaceMarines?.let { them ->
                    them.stream().forEach { println(it) }
                }
            } catch (e: IOException) {
                e.printStackTrace()
                Printer.printError("Связь с сервером разорвана.")
                connectionHandler.close()
            }
        }
    }

    private fun executeScript(filePath: String) {
        try {
            val systemInCommandListener = commandListener
            commandListener = CommandListener(
                FileInputStream(FileManager(filePath).setCheckExecutable(true).open()),
                isEchoOn = false
            )
            while (commandListener.hasNext()) {
                commandListener.readCommand()?.let {
                    if (it.name == "execute_script") {
                        Printer.printError("Произошла попытка вызова скрипта из файла скрипта.")
                    }
                }
            }
            commandListener = systemInCommandListener
        } catch (e: FileException) {
            Printer.printError(e)
        }
    }
}