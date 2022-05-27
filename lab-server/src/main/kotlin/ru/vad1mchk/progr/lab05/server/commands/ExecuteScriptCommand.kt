package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.io.Printer

class ExecuteScriptCommand: AbstractCommand(
    "execute_script",
    "Выполняет команды из файла скрипта.",
    "fileName"
) {
    override fun invoke(request: Request): Response? {
        Printer.printNewLine("Получен скрипт от ${
            if (request.isServerRequest) "сервера" else "клиента"
        }.")
        return null
    }
}