package ru.vad1mchk.progr.lab05.client.application

import ru.vad1mchk.progr.lab05.client.connection.ClientConnectionHandler
import ru.vad1mchk.progr.lab05.client.util.Configuration
import ru.vad1mchk.progr.lab05.common.io.OutputManager
import ru.vad1mchk.progr.lab05.common.io.TerminalInputManager
import java.net.InetAddress
import java.net.UnknownHostException
import java.util.Scanner

/**
 * This class controls the main behavior aspects of the client application.
 */
class ClientApplication {
    companion object {
        const val MAX_PORT_VALUE = (1 shl 16) - 1
        val stringResources = Configuration.STRING_RESOURCES
    }
    val scanner = Scanner(System.`in`)
    var clientConnectionHandler = ClientConnectionHandler()
    val terminalInputManager = TerminalInputManager(Configuration.currentLocale)
    val isListeningAndSending = true

    fun launch() {
        OutputManager.greet()
    }

    fun mainLoop() {

    }

    fun readInetAddress(): InetAddress {
        try {
            OutputManager.sayString(stringResources.getString("input inetAddress"))
            val address = InetAddress.getByName(scanner.nextLine())
            OutputManager.sayInfo(stringResources.getString("UnknownHostException"))
        } catch (e: UnknownHostException) {
            OutputManager.sayException(e)
        }

    }

    fun readPort() {

    }
}