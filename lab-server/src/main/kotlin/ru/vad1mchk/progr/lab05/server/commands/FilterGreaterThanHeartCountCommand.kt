package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.util.SpaceMarineComparator
import ru.vad1mchk.progr.lab05.server.util.Configuration
import java.util.*
import kotlin.streams.toList

class FilterGreaterThanHeartCountCommand: AbstractCommand (
    "filter_greater_than_heart_count",
    "Выводит все элементы, количество сердец у которых больше заданного.",
    "heartCount"
) {
    override fun invoke(request: Request): Response {
        val list = Configuration.COLLECTION_MANAGER
            .stream()
            .filter { compareValues(it.heartCount, request.heartCountArgument!!) > 0 }
            .sorted(SpaceMarineComparator())
            .toList()
        return Response(
            if (list.isEmpty()) "В коллекции нет элементов, удовлетворяющих условию."
            else "Элементы коллекции, удовлетворяющие условию:",
            LinkedList(list)
        )
    }
}