package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.server.commander.CommandInvoker

class HistoryCommand(
    val history: Collection<String>
) : AbstractCommand(
    "history",
    "Выводит названия последних ${CommandInvoker.HISTORY_CAPACITY} команд из истории.",
    null,
    FOR_SERVER_AND_LOGGED_IN_CLIENT
) {
    override fun invoke(request: Request): Response {
        return Response(history.joinToString("\n"))
    }
}