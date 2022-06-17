package ru.vad1mchk.progr.lab05.server.commander

import ru.vad1mchk.progr.lab05.common.collection.CollectionManager
import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.server.commands.*
import ru.vad1mchk.progr.lab05.server.database.DatabaseNegotiator
import java.util.*
import java.util.concurrent.ConcurrentLinkedDeque

/**
 * Class that is responsible for executing the requests obtained from both the client and the server.
 */
class CommandInvoker(
    private val printer: Printer,
    private val collectionManager: CollectionManager<SpaceMarine>,
    private val negotiator: DatabaseNegotiator
) {
    companion object {
        const val HISTORY_CAPACITY = 12
    }

    private val commandMap = TreeMap<String, AbstractCommand>()
    private val commandHistory = ConcurrentLinkedDeque<String>()

    init {
        for (command in arrayOf<AbstractCommand>(
            HelpCommand(),
            UsersCommand(),
            ExitCommand(),
            ExecuteScriptCommand(),
            InfoCommand(collectionManager),
            RegisterCommand(negotiator, printer),
            LoginCommand(negotiator, printer),
            ShowCommand(collectionManager),
            ClearCommand(collectionManager, negotiator, printer),
            AddCommand(collectionManager, negotiator, printer),
            UpdateCommand(collectionManager, negotiator, printer),
            RemoveByIdentifierCommand(collectionManager, negotiator, printer),
            AddIfMinCommand(collectionManager, negotiator, printer),
            RemoveGreaterCommand(collectionManager, negotiator, printer),
            HistoryCommand(commandHistory),
            FilterGreaterThanHeartCountCommand(collectionManager),
            FilterLessThanMeleeWeaponCommand(collectionManager),
            PrintFieldDescendingHealthCommand(collectionManager)
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
            addToHistory(commandName)
            val command = commandMap[commandName]!!
            if (command.canBeInvokedBy(request)) {
                command(request)
            } else Response(
                printer.formatError(
                    "Команда ${request.commandName} недоступна для ${
                        if (request.isServerRequest) "сервера"
                        else if (request.isLoggedInRequest) "зарегистрированного клиента"
                        else "незарегистрированного клиента"
                    }."
                )
            )
        } else {
            Response(printer.formatError("Команда ${request.commandName} не существует."))
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