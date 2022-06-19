package ru.vad1mchk.progr.lab05.client

import javafx.application.Application
import ru.vad1mchk.progr.lab05.client.application.ClientApplication

/** Main entry point of the application from the client's side. */
object Client {
    @JvmStatic
    fun main(args: Array<String>) {
        Application.launch(ClientApplication::class.java, *args)
    }
}