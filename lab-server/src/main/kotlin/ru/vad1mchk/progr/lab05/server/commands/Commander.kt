package ru.vad1mchk.progr.lab05.server.commands

/**
 * Class that manages command execution on the server side.
 */
class Commander {
    companion object {
        /**
         * Amount of commands to store.
         */
        const val HISTORY_CAPACITY = 12
    }

    val commandMap = LinkedHashMap<String, Command>()
    val commandHistory = ArrayDeque<Command>()

    init {
        val commandsToRegister = arrayOf<Command>(
            HelpCommand(),
            InfoCommand(),
            ShowCommand(),
            AddCommand(),
            UpdateCommand(),
            RemoveCommand(),
            ClearCommand(),
            SaveCommand(),
            ExecuteScriptCommand(),
            AddIfMinCommand(),
            RemoveGreaterCommand(),
            HistoryCommand(),
        )
        for (command in commandsToRegister) {
            commandMap[command.commandName] = command
        }
    }
}