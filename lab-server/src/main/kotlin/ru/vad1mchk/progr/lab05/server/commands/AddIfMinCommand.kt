package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.collection.CollectionManager
import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.common.exceptions.DatabaseException
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.server.database.DatabaseNegotiator

class AddIfMinCommand(
    val collectionManager: CollectionManager<SpaceMarine>,
    val negotiator: DatabaseNegotiator,
    val printer: Printer
): AbstractCommand(
    "add_if_min",
    "Добавляет новый элемент в коллекцию, если его значение меньше, чем у всех элементов этой коллекции.",
    "{element}",
    FOR_SERVER_AND_LOGGED_IN_CLIENT
) {
    override fun invoke(request: Request): Response {
        if (collectionManager.collection().stream().anyMatch { it <= request.spaceMarineArgument!! }) {
            return Response(
                printer.formatError("Элемент не был добавлен: найдены элементы, которые меньше либо равны ему.")
            )
        }
        return try {
            negotiator.insertSpaceMarine(request.spaceMarineArgument!!, request.user)
            collectionManager.addPreservingID(request.spaceMarineArgument!!)
            Response("Элемент успешно добавлен в коллекцию.")
        } catch (e: DatabaseException) {
            Response(printer.formatError(e))
        }
    }
}