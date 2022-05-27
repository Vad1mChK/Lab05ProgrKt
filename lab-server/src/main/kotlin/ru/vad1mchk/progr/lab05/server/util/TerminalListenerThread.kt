package ru.vad1mchk.progr.lab05.server.util

import ru.vad1mchk.progr.lab05.common.communication.EnteredCommand
import ru.vad1mchk.progr.lab05.common.communication.RequestCreator
import ru.vad1mchk.progr.lab05.common.exceptions.FileException
import ru.vad1mchk.progr.lab05.common.file.FileManager
import ru.vad1mchk.progr.lab05.common.io.CommandListener
import ru.vad1mchk.progr.lab05.common.io.Printer
import java.io.FileInputStream
import kotlin.system.exitProcess

class TerminalListenerThread: Thread() {
    var commandListener = CommandListener(
        System.`in`,
        true
    )

    override fun run() {
        while (Configuration.isWorking) {
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
                executeRequest(enteredCommand)
            }
        }
    }

    private fun executeRequest(enteredCommand: EnteredCommand) {
        val requestCreator = RequestCreator()
        val request = requestCreator.requestFromEnteredCommand(enteredCommand)
        if (request != null) {
            request.isServerRequest = true
            val response = Configuration.COMMAND_INVOKER.executeRequest(request)
            response?.stringMessage?.let { if(it.isNotEmpty()) Printer.printNewLine(it) }
            response?.spaceMarines?.stream()?.forEach { println(it) }
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