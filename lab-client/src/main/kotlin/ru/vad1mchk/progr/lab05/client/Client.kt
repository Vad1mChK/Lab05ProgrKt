package ru.vad1mchk.progr.lab05.client

import ru.vad1mchk.progr.lab05.client.application.ClientApplication
import ru.vad1mchk.progr.lab05.common.io.OutputManager
import java.net.InetAddress
import java.net.Socket

/**
 * Main entry point of the program from the client side.
 */
object Client {
    /**
     * Main entry point of the program from the client side.
     * @param args Arguments of the program.
     */
    @JvmStatic
    fun main(args: Array<String>) {
        ClientApplication().launch()
    }
}