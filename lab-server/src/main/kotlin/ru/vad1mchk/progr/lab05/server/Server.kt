package ru.vad1mchk.progr.lab05.server

import ru.vad1mchk.progr.lab05.common.exceptions.FileCannotOpenException
import ru.vad1mchk.progr.lab05.common.exceptions.FileException
import ru.vad1mchk.progr.lab05.common.file.FileManager
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.server.application.ServerApplication
import ru.vad1mchk.progr.lab05.server.csv.CsvDeserializer
import ru.vad1mchk.progr.lab05.server.util.Configuration

/** Main entry point of the application from the server's side. */
object Server {
    @JvmStatic
    fun main(args: Array<String>) {
        ServerApplication().launch(args)
    }
}