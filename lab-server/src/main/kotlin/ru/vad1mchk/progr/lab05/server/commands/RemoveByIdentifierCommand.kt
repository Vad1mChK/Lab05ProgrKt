package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.exceptions.IdentifierNotExistsException
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.server.util.Configuration

class RemoveByIdentifierCommand: AbstractCommand (
    "remove_by_id",
    "Удаляет элемент из коллекции по его ID.",
    "id"
) {
    override fun invoke(request: Request): Response? {
        return try {
            Configuration.COLLECTION_MANAGER.removeById(request.idArgument!!)
            Response("Элемент удалён из коллекции.")
        } catch (e: IdentifierNotExistsException) {
            Response(Printer.formatError(e.message ?: ""))
        }
    }
}