package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.server.commander.CommandInvoker
import ru.vad1mchk.progr.lab05.server.util.Configuration
import java.util.stream.Collectors

class HistoryCommand: AbstractCommand(
    "history",
    "Выводит названия последних ${CommandInvoker.HISTORY_CAPACITY} команд из истории."
) {
    override fun invoke(request: Request): Response {
        return Response(Configuration.COMMAND_INVOKER.commandHistory.stream().collect(Collectors.joining("\n")))
    }
}