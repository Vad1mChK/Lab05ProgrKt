package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response

class HelpCommand: AbstractCommand(
    "help",
    "Выводит справку по доступным командам."
) {
    override fun invoke(request: Request): Response {
        return Response(commandsList
            .filter { !it.isServerOnly || request.isServerRequest }
            .map { it.getHelpString() }
            .joinToString("\n"))
    }
}