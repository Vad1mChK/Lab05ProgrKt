package ru.vad1mchk.progr.lab05.client.terminal

import ru.vad1mchk.progr.lab05.client.commands.*
import ru.vad1mchk.progr.lab05.client.exceptions.InvalidCommandNameException
import ru.vad1mchk.progr.lab05.client.messages.Messages

object Invoker {
    private val commandMap = HashMap<String, Command>()

    init {
        register("help", Help())
        register("info", Info())
        register("show", Show())
        register("clear", Clear())
        register("exit", Exit())
        register("history", History())
        register("print_field_descending_health", PrintFieldDescendingHealth())
    }

    fun register(commandName: String, command: Command) {
        commandMap[commandName] = command
    }

    fun execute(commandName: String) {
        val command: Command =
            commandMap[commandName] ?:
            throw InvalidCommandNameException(String.format(Messages["errorInvalidCommandName"], commandName))
        HistoryStorage.add(commandName)
        command.execute()
    }
}