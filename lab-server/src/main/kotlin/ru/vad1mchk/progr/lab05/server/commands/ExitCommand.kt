package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.io.Printer

class ExitCommand: AbstractCommand(
    "exit",
    "Завершает работу приложения."
) {
    override fun invoke(request: Request): Response? {
        return null
    }
}