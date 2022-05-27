package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.exceptions.IdentifierNotExistsException
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.server.util.Configuration

class UpdateCommand: AbstractCommand (
    "update",
    "Обновляет значение элемента коллекции, ID которого равен заданному.",
    "id {element}"
) {
    override fun invoke(request: Request): Response? {
        return try {
            Configuration.COLLECTION_MANAGER.update(request.idArgument!!, request.spaceMarineArgument!!)
            Response("Элемент обновлён в коллекции.")
        } catch (e: IdentifierNotExistsException) {
            Response(Printer.formatError(e.message ?: ""))
        }
    }
}