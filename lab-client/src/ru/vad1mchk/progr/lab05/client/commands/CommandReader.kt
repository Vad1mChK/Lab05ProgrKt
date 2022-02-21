package ru.vad1mchk.progr.lab05.client.commands

import ru.vad1mchk.progr.lab05.client.exceptions.CommandException
import ru.vad1mchk.progr.lab05.client.exceptions.EmptyCommandException
import ru.vad1mchk.progr.lab05.client.messages.Messages
import java.io.InputStream
import java.util.*

class CommandReader(stream: InputStream?) {
    var scanner: Scanner

    init {
        scanner = Scanner(stream!!)
    }

    /**
     * Method that reads and parses command, throwing an exception if needed.
     *
     * Note that strings starting with '#' are treated as comments and are not parsed.
     * @return parsed command
     */
    fun read(): Command? {
        print(Messages.get("inputAsk"))
        var cmd: Command? = null
        val str = scanner.nextLine().trim(' ')
        if (StringBuffer(str).startsWith('#')) {
            return null
        }
        try {
            cmd = Command(str)
        } catch (e: CommandException) {
            if (e !is EmptyCommandException) {
                println("[\u001b[32mError\u001b[0m] " + e.message)
            }
        }
        return cmd
    }
}