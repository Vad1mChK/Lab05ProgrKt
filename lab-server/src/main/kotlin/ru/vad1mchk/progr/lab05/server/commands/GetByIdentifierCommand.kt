package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.collection.CollectionManager
import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.common.exceptions.CollectionException
import ru.vad1mchk.progr.lab05.common.io.Printer
import java.util.*

class GetByIdentifierCommand(
    val collectionManager: CollectionManager<SpaceMarine>,
    val printer: Printer
) : AbstractCommand(
    "get_by_id",
    "Возвращает космического десантника с таким ID, если есть.",
    "id",
    FOR_SERVER_AND_LOGGED_IN_CLIENT
) {
    override fun invoke(request: Request): Response {
        return try {
            val element: SpaceMarine = collectionManager[request.idArgument!!]
            Response("Найден элемент с ID #${request.idArgument}", LinkedList(listOf(element)))
        } catch (e: CollectionException) {
            Response(printer.formatError(e))
        }
    }
}