package ru.vad1mchk.progr.lab05.server.commander

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.exceptions.InvalidDataException
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.server.commands.*
import java.util.*

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

    fun executeRequest(request: Request): Response? {
        val commandName = request.commandName.lowercase()
        return if (commandMap.containsKey(commandName)) {
            if (!request.isServerRequest && !commandMap[commandName]!!.isServerOnly
            ) {
                try {
                    addToHistory(commandName)
                    commandMap[commandName]!!(request)
                } catch (e: InvalidDataException) {
                    Response(Printer.formatError(e.message?:""))
                }
            } else if (request.isServerRequest) {
                try {
                    addToHistory(commandName)
                    commandMap[commandName]!!(request)
                } catch (e: IllegalArgumentException) {
                     Response(Printer.formatError(e.message?:""))
                }
            } else {
                Response(Printer.formatError("Команда ${request.commandName} недоступна для клиента."))
            }
        } else {
            Response(Printer.formatError("Команда ${request.commandName} не существует."))
        }
    }

    fun addToHistory(commandName: String) {
        if (commandHistory.size == HISTORY_CAPACITY) {
            commandHistory.removeLast()
        }
        commandHistory.addFirst(commandName)
    }
}