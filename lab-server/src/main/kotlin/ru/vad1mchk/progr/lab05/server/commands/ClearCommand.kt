package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.server.util.Configuration

class ClearCommand: AbstractCommand(
    "clear",
    "Очищает коллекцию."
) {
    override fun invoke(request: Request): Response {
        Configuration.COLLECTION_MANAGER.clear()
        return Response("Коллекция успешно очищена от элементов.")
    }
}