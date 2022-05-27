package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.common.util.SpaceMarineComparator
import ru.vad1mchk.progr.lab05.server.util.Configuration
import java.util.*
import kotlin.collections.ArrayList
import kotlin.streams.toList

class ShowCommand: AbstractCommand(
    "show",
    "Выводит все элементы коллекции в строковом представлении."
) {
    override fun invoke(request: Request): Response {
        return Response(
            if (Configuration.COLLECTION_MANAGER.size() > 0) "Элементы коллекции:" else "В коллекции нет элементов.",
            LinkedList(
                Configuration.COLLECTION_MANAGER
                .stream()
                .sorted(SpaceMarineComparator())
                .toList()
            )
        )
    }
}