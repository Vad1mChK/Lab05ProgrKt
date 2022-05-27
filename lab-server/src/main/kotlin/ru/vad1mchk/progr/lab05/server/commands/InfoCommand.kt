package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.server.util.Configuration

class InfoCommand: AbstractCommand(
    "info",
    "Выводит основную информацию о коллекции космических десантников."
) {
    override fun invoke(request: Request): Response {
        return Response(Configuration.COLLECTION_MANAGER.info())
    }
}