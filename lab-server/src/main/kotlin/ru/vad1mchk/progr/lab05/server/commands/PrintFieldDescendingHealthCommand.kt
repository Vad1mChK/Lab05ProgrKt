package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.server.util.Configuration
import java.util.stream.Collectors

class PrintFieldDescendingHealthCommand: AbstractCommand(
    "print_field_descending_health",
    "Выводит значения здоровья всех элементов в порядке невозрастания."
) {
    override fun invoke(request: Request): Response? {
        val healths = Configuration.COLLECTION_MANAGER
            .stream()
            .map { it.health }
            .sorted(Comparator.reverseOrder())
            .map { it.toString() }
            .collect(Collectors.joining("; "))
        return Response("Здоровье в порядке убывания:\n$healths")
    }
}