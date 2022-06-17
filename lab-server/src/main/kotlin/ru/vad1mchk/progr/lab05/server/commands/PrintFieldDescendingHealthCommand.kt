package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.collection.CollectionManager
import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import java.util.stream.Collectors

class PrintFieldDescendingHealthCommand(
    val collectionManager: CollectionManager<SpaceMarine>
): AbstractCommand (
    "print_field_descending_health",
    "Выводит значения здоровья всех элементов в порядке невозрастания.",
    null,
    FOR_SERVER_AND_LOGGED_IN_CLIENT
) {
    override fun invoke(request: Request): Response {
        val healths = collectionManager
            .stream()
            .map { it.health }
            .sorted(Comparator.reverseOrder())
            .map { it.toString() }
            .collect(Collectors.joining("; "))
        return Response("Здоровье в порядке убывания:\n$healths")
    }
}