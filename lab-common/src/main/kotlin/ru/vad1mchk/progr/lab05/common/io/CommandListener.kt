package ru.vad1mchk.progr.lab05.common.io

import ru.vad1mchk.progr.lab05.common.communication.EnteredCommand
import java.io.InputStream
import java.util.*
import kotlin.system.exitProcess

/**
 * Command listener that accepts commands in interactive mode and creates instances of [EnteredCommand] to store
 * command name and arguments temporarily.
 * @property isServer If this command listener is on the server's side.
 * @property userName Username of the caller.
 * @property isEchoOn If the input invitation should be displayed.
 * @constructor Constructs a new command listener that listens to the specified input stream.
 * @param input Input stream to use the scanner for.
 */
class CommandListener(
    input: InputStream,
    private val isServer: Boolean = false,
    private val userName: String = "JohnDoe",
    private val isEchoOn: Boolean = true,
    private val printer: Printer
) {
    private val scanner: Scanner

    init {
        scanner = Scanner(input)
    }

    /**
     * Reads the command from the user input.
     * @return The entered command wrapper.
     */
    fun readCommand(): EnteredCommand? {
        if (isEchoOn) printer.inviteInput(isServer, userName)
        try {
            val line = scanner.nextLine()
            return EnteredCommand.fromString(line)
        } catch (e: NoSuchElementException) {
            exitProcess(0)
        }
    }
}