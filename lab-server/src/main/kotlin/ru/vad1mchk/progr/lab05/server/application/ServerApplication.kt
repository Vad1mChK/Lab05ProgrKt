package ru.vad1mchk.progr.lab05.server.application

import com.fasterxml.jackson.core.JsonParseException
import ru.vad1mchk.progr.lab05.common.application.AbstractApplication
import ru.vad1mchk.progr.lab05.common.exceptions.FileCannotOpenException
import ru.vad1mchk.progr.lab05.common.exceptions.FileException
import ru.vad1mchk.progr.lab05.common.file.FileManager
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.server.connection.ServerConnectionHandler
import ru.vad1mchk.progr.lab05.server.csv.CsvDeserializer
import ru.vad1mchk.progr.lab05.server.util.Configuration
import ru.vad1mchk.progr.lab05.server.util.TerminalListenerThread
import java.io.IOException
import kotlin.system.exitProcess

class ServerApplication: AbstractApplication() {
    lateinit var connectionHandler: ServerConnectionHandler
    val terminalListenerThread = TerminalListenerThread()

    override fun launch(args: Array<String>) {
        Printer.printNewLine("Сервер менеджера космических десантников приветствует вас.")
        Printer.printNewLine("Загружается файл коллекции...")
        pickFile(args)
        try {
            connectionHandler = ServerConnectionHandler(readPort())
            terminalListenerThread.start()
            connectionHandler.run()
        } catch (e: IOException) {
            Printer.printError("Во время открытия порта произошла ошибка ввода-вывода.")
        }
    }

    private fun pickFile(args: Array<String>) {
        Configuration.collectionFilePath = when(args.size) {
            0 -> {
                Printer.printError("Вы не указали путь к файлу коллекции.")
                readFileName()
            }
            1 -> {
                try {
                    FileManager(args[0]).setCheckReadable(true).setCheckWritable(true).open()
                    args[0]
                } catch (e: FileCannotOpenException) {
                    Printer.printError(e)
                    readFileName()
                }
            }
            else -> {
                Printer.printError("Слишком много аргументов программы.")
                readFileName()
            }
        }
        try {
            CsvDeserializer(Configuration.collectionFilePath).readAll()
            Printer.printNewLine("Файл коллекции ${Configuration.collectionFilePath} загружен.")
        } catch (e: JsonParseException) {
            Printer.printError("Данные в файле хранятся в неверном виде. Загружена пустая коллекция, при сохранении" +
                    " старые элементы будут удалены.")
        }
    }

    private fun readFileName(): String {
        while (true) {
            Printer.printNoNewLine("Введите путь к файлу коллекции: ")
            try {
                val fileName = scanner.nextLine()
                FileManager(fileName).setCheckReadable(true).setCheckWritable(true).open()
                return fileName
            } catch (e: Exception) {
                if (e is FileException) {
                    Printer.printError(e)
                }
                if (e is NoSuchElementException) {
                    exitProcess(0)
                }
            }
        }
    }
}