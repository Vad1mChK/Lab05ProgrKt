package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.collection.CollectionManager
import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.common.exceptions.DatabaseException
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.common.util.SpaceMarineComparator
import java.util.*

class ShowCommand(
    private val collectionManager: CollectionManager<SpaceMarine>,
): AbstractCommand(
    "show",
    "Выводит все элементы коллекции в строковом представлении.",
    null,
    FOR_SERVER_AND_LOGGED_IN_CLIENT
) {
    override fun invoke(request: Request): Response {
        return Response("Элементов в коллекции: ${
                collectionManager.collection().size
            }.", LinkedList(
                collectionManager
                    .collection()
                    .sortedWith(SpaceMarineComparator())
            )
        )
    }
}