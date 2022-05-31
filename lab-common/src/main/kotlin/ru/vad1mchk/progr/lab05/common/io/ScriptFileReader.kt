package ru.vad1mchk.progr.lab05.common.io

import ru.vad1mchk.progr.lab05.common.commands.AvailableCommands
import ru.vad1mchk.progr.lab05.common.communication.EnteredCommand
import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.RequestCreator
import ru.vad1mchk.progr.lab05.common.file.FileManager
import java.io.File
import java.util.*

/**
 * Class that reads and interprets the requests from file.
 * @constructor Creates a new [ScriptFileReader] that reads the requests from file located by `filePath`.
 * @param filePath Path of the file to open.
 */
class ScriptFileReader(filePath: String) {
    private val file: File
    private val requestDeque: ArrayDeque<Request>
    private val scanner: Scanner
    private val requestCreator: RequestCreator

    init {
        file = FileManager(filePath)
            .setCheckExecutable(true)
            .setCheckReadable(true)
            .open()
        scanner = Scanner(file.inputStream())
        requestCreator = RequestCreator(scanner)
        requestDeque = ArrayDeque()
    }

    /**
     * Reads all requests from the script file.
     * @return An [ArrayDeque] containing all the obtained requests.
     */
    fun readAll(): ArrayDeque<Request> {
        var currentEnteredCommand: EnteredCommand?
        while (scanner.hasNext()) {
            try {
                currentEnteredCommand = EnteredCommand.fromString(scanner.nextLine())
                if (currentEnteredCommand != null) {
                    if (currentEnteredCommand.name == "execute_script" && currentEnteredCommand.arguments.size == 1) {
                        Printer.printError("Попытка вызвать скрипт из другого скрипта.")
                    } else {
                        val request = requestCreator.requestFromEnteredCommand(currentEnteredCommand)
                        request?.let { requestDeque.add(it) }
                    }
                }
            } catch (e: NoSuchElementException) {
                Printer.printNewLine("Ввод скрипта из файла завершён.")
                return requestDeque
            }
        }
        Printer.printNewLine("Ввод скрипта из файла завершён.")
        return requestDeque
    }
}