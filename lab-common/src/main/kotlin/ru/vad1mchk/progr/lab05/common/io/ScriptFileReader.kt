package ru.vad1mchk.progr.lab05.common.io

import ru.vad1mchk.progr.lab05.common.commands.AvailableCommands
import ru.vad1mchk.progr.lab05.common.communication.EnteredCommand
import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.RequestCreator
import ru.vad1mchk.progr.lab05.common.file.FileManager
import java.io.File
import java.util.*

class ScriptFileReader(filePath: String) {
    private val file: File
    private val requestDeque: ArrayDeque<Request>
    private val scanner: Scanner
    private val requestCreator: RequestCreator
    private var isReadingSpaceMarine: Boolean = false

    init {
        file = FileManager(filePath).setCheckExecutable(true).open()
        scanner = Scanner(file.inputStream())
        requestCreator = RequestCreator(scanner)
        requestDeque = ArrayDeque()
    }

    fun readAll(): ArrayDeque<Request> {
        var currentLine: String? = null
        var currentEnteredCommand: EnteredCommand? = null
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
            }
        }
        return requestDeque
    }
}