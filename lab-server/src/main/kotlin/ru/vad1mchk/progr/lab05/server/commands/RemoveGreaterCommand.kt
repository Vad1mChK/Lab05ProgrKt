package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.collection.CollectionManager
import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.common.exceptions.CollectionException
import ru.vad1mchk.progr.lab05.common.exceptions.DatabaseException
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.server.database.DatabaseNegotiator

class RemoveGreaterCommand(
    val collectionManager: CollectionManager<SpaceMarine>,
    val negotiator: DatabaseNegotiator,
    val printer: Printer
) : AbstractCommand(
    "remove_greater",
    "Удаляет из коллекции все элементы, превышающие заданный.",
    "{element}",
    FOR_SERVER_AND_LOGGED_IN_CLIENT
) {
    override fun invoke(request: Request): Response? {
        return try {
            for (element in collectionManager.collection().filter {
                (it.creatorName == request.user?.userName || request.user == null) &&
                        it > request.spaceMarineArgument!!
            }) {
                negotiator.deleteSpaceMarineById(element.id)
                collectionManager.removeById(element.id)
            }
            Response("Элементы успешно удалены из коллекции.")
        } catch (e: DatabaseException) {
            Response(printer.formatError(e))
        } catch (e: CollectionException) {
            Response(printer.formatError(e))
        }
    }
}