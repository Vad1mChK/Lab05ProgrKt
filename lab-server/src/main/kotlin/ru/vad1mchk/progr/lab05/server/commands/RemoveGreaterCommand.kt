package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.commands.Command
import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.exceptions.IdentifierNotExistsException
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.server.util.Configuration
import java.util.stream.Collector

class RemoveGreaterCommand: AbstractCommand(
    "remove_greater",
    "Удаляет из коллекции все элементы, превышающие заданный."
) {
    override fun invoke(request: Request): Response? {
        return try {
            Configuration.COLLECTION_MANAGER.collection().removeIf { it > request.spaceMarineArgument!! }
            Response("Элементы удалены из коллекции.")
        } catch (e: IdentifierNotExistsException) {
            Response(Printer.formatError(e.message ?: ""))
        }
    }
}