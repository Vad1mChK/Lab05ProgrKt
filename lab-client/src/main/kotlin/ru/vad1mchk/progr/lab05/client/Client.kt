package ru.vad1mchk.progr.lab05.client

import ru.vad1mchk.progr.lab05.client.application.ClientApplication

/** Main entry point of the application from the client's side. */
object Client {
    @JvmStatic
    fun main(args: Array<String>) {
        ClientApplication().launch()
    }
}