package ru.vad1mchk.progr.lab05.server.util

import ru.vad1mchk.progr.lab05.common.communication.EnteredCommand
import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.RequestCreator
import ru.vad1mchk.progr.lab05.common.exceptions.FileException
import ru.vad1mchk.progr.lab05.common.file.FileManager
import ru.vad1mchk.progr.lab05.common.io.CommandListener
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.common.io.ScriptFileReader
import ru.vad1mchk.progr.lab05.server.commander.CommandInvoker
import java.io.FileInputStream
import java.util.*
import kotlin.system.exitProcess

/**
 * An additional thread that listens for the terminal input on the server's side.
 */
class TerminalListenerThread(
    val commandInvoker: CommandInvoker, val printer: Printer
): Thread() {
    var commandListener = CommandListener(
        System.`in`, true, "server", true, printer
    )
    val requestCreator = RequestCreator(null, Scanner(System.`in`), printer)

    override fun run() {
        while (true) {
            val enteredCommand = commandListener.readCommand()
            if (enteredCommand != null) {
                if ("exit" == enteredCommand.name.lowercase()) {
                    if (enteredCommand.arguments.isEmpty()) {
                        printer.printNewLine("Завершение работы.")
                        exitProcess(0)
                    } else {
                        printer.printError("Для того чтобы выйти из приложения, введите команду exit без аргументов.")
                    }
                } else if ("execute_script" == enteredCommand.name.lowercase()) {
                    if (enteredCommand.arguments.size == 1) {
                        executeScript(enteredCommand.arguments[0])
                    } else {
                        printer.printError("Неверное количество аргументов команды.")
                    }
                }
                executeRequest(requestCreator.requestFromEnteredCommand(enteredCommand))
            }
        }
    }

    /**
     * Executes the specified request using the command invoker.
     * @param request Request to execute.
     */
    private fun executeRequest(request: Request?) {
        if (request != null) {
            request.isServerRequest = true
            val response = commandInvoker.executeRequest(request)
            response?.stringMessage?.let { if(it.isNotEmpty()) printer.printNewLine(it) }
            response?.spaceMarines?.stream()?.forEach { println(it) }
        }
    }

    /**
     * Executes the specified script file's commands one by one.
     * @param filePath Path of script file to execute.
     */
    private fun executeScript(filePath: String) {
        try {
            val scriptFileReader = ScriptFileReader(filePath, null, printer)
            for (request in scriptFileReader.readAll()) {
                executeRequest(request)
            }
        } catch (e: FileException) {
            printer.printError(e)
        }
    }
}