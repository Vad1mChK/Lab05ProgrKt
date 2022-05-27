package ru.vad1mchk.progr.lab05.common.io

import ru.vad1mchk.progr.lab05.common.communication.EnteredCommand
import java.io.InputStream
import java.nio.charset.Charset
import java.util.Scanner
import kotlin.system.exitProcess

class CommandListener(
    input: InputStream,
    isServer: Boolean = false,
    userName: String = "JohnDoe",
    isEchoOn: Boolean = true
) {
    private val scanner: Scanner
    private val isServer: Boolean
    private val userName: String
    private val isEchoOn: Boolean

    init {
        scanner = Scanner(input)
        this.isServer = isServer
        this.userName = userName
        this.isEchoOn = isEchoOn
    }

    fun readCommand(): EnteredCommand? {
        if (isEchoOn) Printer.inviteInput(isServer, userName)
        try {
            val line = scanner.nextLine()
            return EnteredCommand.fromString(line)
        } catch (e: NoSuchElementException) {
            exitProcess(0)
        }
    }

    fun hasNext(): Boolean {
        return scanner.hasNext()
    }
}