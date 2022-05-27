package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.server.csv.CsvSerializer
import ru.vad1mchk.progr.lab05.server.util.Configuration
import java.io.IOException

class SaveCommand: AbstractCommand (
    "save",
    "Сохраняет коллекцию в файл.",
    null,
    true
) {
    override fun invoke(request: Request): Response? {
        return try {
            val csvSerializer = CsvSerializer(Configuration.collectionFilePath)
            csvSerializer.writeAllToFile()
            Response("Коллекция успешно сохранена в файл.")
        } catch (e: IOException) {
            Response(Printer.formatError(e.message ?: ""))
        }
    }
}