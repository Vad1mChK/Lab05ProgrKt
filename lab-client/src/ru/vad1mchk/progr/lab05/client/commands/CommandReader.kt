package ru.vad1mchk.progr.lab05.client.commands

import ru.vad1mchk.progr.lab05.client.exceptions.CommandException
import ru.vad1mchk.progr.lab05.client.exceptions.EmptyCommandException
import ru.vad1mchk.progr.lab05.client.exceptions.IllegalCommandNameException
import java.io.InputStream
import java.util.*

class CommandReader(stream: InputStream?) {
    var scanner: Scanner

    init {
        scanner = Scanner(stream)
    }

    /**
     * Method that reads and parses command, throwing an exception if needed
     *
     * @return parsed command
     */
    fun read(): Command? {
        var cmd: Command? = null
        val str = scanner.nextLine().trim { it <= ' ' }
        try {
            cmd = Command(str)
            println("\tName: " + cmd.commandType.cmdName)
            if (cmd.hasArg()) {
                println("\tArg: " + cmd.commandArg)
            }
        } catch (e: CommandException) {
            println("[\u001b[32mError\u001b[0m] " + e.message)
        }
        return cmd
    }
}