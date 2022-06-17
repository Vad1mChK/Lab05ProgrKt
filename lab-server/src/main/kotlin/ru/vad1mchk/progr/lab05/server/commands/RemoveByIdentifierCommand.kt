package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.collection.CollectionManager
import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.common.exceptions.CollectionException
import ru.vad1mchk.progr.lab05.common.exceptions.DatabaseException
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.server.database.DatabaseNegotiator

class RemoveByIdentifierCommand(
    val collectionManager: CollectionManager<SpaceMarine>,
    val negotiator: DatabaseNegotiator,
    val printer: Printer
) : AbstractCommand(
    "remove_by_id",
    "Удаляет элемент из коллекции по его id.",
    "id",
    FOR_SERVER_AND_LOGGED_IN_CLIENT
) {
    override fun invoke(request: Request): Response? {
        println(request)
        request.user?.let {
            if (collectionManager[request.idArgument!!].creatorName != request.user!!.userName) {
                return Response(
                    printer.formatError("Невозможно удалить элемент: он принадлежит другому пользователю.")
                )
            }
        }
        return try {
            negotiator.deleteSpaceMarineById(request.idArgument!!)
            collectionManager.removeById(request.idArgument!!)
            Response("Элемент успешно удалён из коллекции.")
        } catch (e: DatabaseException) {
            Response(printer.formatError(e))
        } catch (e: CollectionException) {
            Response(printer.formatError(e))
        }
    }
}