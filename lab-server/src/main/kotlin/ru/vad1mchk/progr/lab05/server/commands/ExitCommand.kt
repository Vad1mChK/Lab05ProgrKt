package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response

class ExitCommand: AbstractCommand(
    "exit",
    "Завершает работу приложения.",
    null,
    FOR_ALL
) {
    override fun invoke(request: Request): Response? {
        return null
    }
}