package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.util.SpaceMarineComparator
import ru.vad1mchk.progr.lab05.server.util.Configuration
import java.util.LinkedList
import kotlin.streams.toList

class FilterLessThanMeleeWeaponCommand: AbstractCommand (
    "filter_less_than_melee_weapon",
    "Выводит все элементы, оружие ближнего боя у которых меньше заданного.",
    "meleeWeapon"
) {
    override fun invoke(request: Request): Response {
        val list = Configuration.COLLECTION_MANAGER
            .stream()
            .filter { compareValues(it.meleeWeapon, request.meleeWeaponArgument!!) < 0 }
            .sorted(SpaceMarineComparator())
            .toList()
        return Response(
            if (list.isEmpty()) "В коллекции нет элементов, удовлетворяющих условию."
            else "Элементы коллекции, удовлетворяющие условию:",
            LinkedList(list)
        )
    }
}