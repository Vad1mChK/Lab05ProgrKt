package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response

class ExecuteScriptCommand: AbstractCommand(
    "execute_script",
    "Выполняет скрипт, расположенный по пути file_name.",
    "file_name",
    FOR_SERVER_AND_LOGGED_IN_CLIENT
) {
    override fun invoke(request: Request): Response? {
        return null
    }
}