package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.collection.CollectionManager
import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.common.exceptions.DatabaseException
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.server.database.DatabaseNegotiator

class AddCommand(
    val collectionManager: CollectionManager<SpaceMarine>,
    val negotiator: DatabaseNegotiator,
    val printer: Printer
) : AbstractCommand(
    "add",
    "Добавляет новый элемент в коллекцию.",
    "{element}",
    FOR_SERVER_AND_LOGGED_IN_CLIENT
) {
    override fun invoke(request: Request): Response? {
        println(request)
        return try {
            negotiator.insertSpaceMarine(request.spaceMarineArgument!!, request.user)
            collectionManager.addPreservingID(request.spaceMarineArgument!!)
            Response("Элемент успешно добавлен в коллекцию.")
        } catch (e: DatabaseException) {
            Response(printer.formatError(e))
        }
    }
}