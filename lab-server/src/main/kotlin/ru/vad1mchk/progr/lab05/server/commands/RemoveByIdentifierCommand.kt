package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.collection.CollectionManager
import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.common.exceptions.CollectionException
import ru.vad1mchk.progr.lab05.common.exceptions.DatabaseException
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.common.util.SpaceMarineComparator
import ru.vad1mchk.progr.lab05.server.database.DatabaseNegotiator
import java.util.*

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
        return try {
            request.user?.let {
                if (collectionManager[request.idArgument!!].creatorName != it.userName) {
                    Response(
                        printer.formatError("Невозможно удалить элемент: он принадлежит другому пользователю.")
                    )
                }
            }
            negotiator.deleteSpaceMarineById(request.idArgument!!)
            collectionManager.removeById(request.idArgument!!)
            Response("Элемент успешно удалён из коллекции.", notification = true,
                spaceMarines = LinkedList(collectionManager.collection().sortedWith(SpaceMarineComparator()))
            )
        } catch (e: DatabaseException) {
            Response(printer.formatError(e))
        } catch (e: CollectionException) {
            Response(printer.formatError(e))
        }
    }
}