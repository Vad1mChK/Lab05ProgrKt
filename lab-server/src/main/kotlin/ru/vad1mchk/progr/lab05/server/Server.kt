package ru.vad1mchk.progr.lab05.server

import ru.vad1mchk.progr.lab05.server.application.ServerApplication

/** Main entry point of the application from the server's side. */
object Server {
    @JvmStatic
    fun main(args: Array<String>) {
        ServerApplication().launch(args)
    }
}