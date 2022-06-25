package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.collection.CollectionManager
import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.common.exceptions.DatabaseException
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.common.util.SpaceMarineComparator
import ru.vad1mchk.progr.lab05.server.database.DatabaseNegotiator
import java.util.*

class ClearCommand(
    val collectionManager: CollectionManager<SpaceMarine>,
    val negotiator: DatabaseNegotiator,
    val printer: Printer
) : AbstractCommand(
    "clear",
    "Очищает все элементы коллекции, доступные данному пользователю.",
    null,
    FOR_SERVER_AND_LOGGED_IN_CLIENT
) {
    override fun invoke(request: Request): Response? {
        return try {
            negotiator.deleteSpaceMarinesByUser(request.user)
            collectionManager.clear(request.user)
            Response("Очищены все элементы коллекции${
                request.user?.let { ", принадлежащие пользователю ${it.userName}" } ?: ""
            }.", notification = true,
                spaceMarines = LinkedList(collectionManager.collection().sortedWith(SpaceMarineComparator())))
        } catch (e: DatabaseException) {
            Response(printer.formatError(e))
        }
    }
}