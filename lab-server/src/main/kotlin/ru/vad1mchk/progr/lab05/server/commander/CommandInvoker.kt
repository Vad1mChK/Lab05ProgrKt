package ru.vad1mchk.progr.lab05.server.commander

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.exceptions.InvalidDataException
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.server.commands.*
import java.util.*

/**
 * Class that is responsible for executing the requests obtained from both the client and the server.
 */
class CommandInvoker {
    companion object {
        const val HISTORY_CAPACITY = 12
    }
    private val commandMap = TreeMap<String, AbstractCommand>()
    val commandHistory = ArrayDeque<String>()

    init {
        for (command in arrayOf<AbstractCommand>(
            HelpCommand(),
            InfoCommand(),
            ShowCommand(),
            AddCommand(),
            UpdateCommand(),
            RemoveByIdentifierCommand(),
            ClearCommand(),
            SaveCommand(),
            ExitCommand(),
            ExecuteScriptCommand(),
            AddIfMinCommand(),
            RemoveGreaterCommand(),
            HistoryCommand(),
            FilterLessThanMeleeWeaponCommand(),
            FilterGreaterThanHeartCountCommand(),
            PrintFieldDescendingHealthCommand()
        )) commandMap[command.name] = command
    }

    /**
     * Executes the specified request, invoking a command on it, and returns a response.
     * @param request Request to execute.
     * @return Response that is the result of the command invocation.
     */
    fun executeRequest(request: Request): Response? {
        val commandName = request.commandName.lowercase()
        return if (commandMap.containsKey(commandName)) {
            if (!request.isServerRequest && !commandMap[commandName]!!.isServerOnly
                || request.isServerRequest) {
                try {
                    addToHistory(commandName)
                    commandMap[commandName]!!(request)
                } catch (e: InvalidDataException) {
                    Response(Printer.formatError(e.message?:""))
                }
            } else {
                Response(Printer.formatError("Команда ${request.commandName} недоступна для клиента."))
            }
        } else {
            Response(Printer.formatError("Команда ${request.commandName} не существует."))
        }
    }

    /**
     * Adds this command's name to the command history.
     * @param commandName Command name to add.
     */
    private fun addToHistory(commandName: String) {
        if (commandHistory.size == HISTORY_CAPACITY) {
            commandHistory.removeLast()
        }
        commandHistory.addFirst(commandName)
    }
}