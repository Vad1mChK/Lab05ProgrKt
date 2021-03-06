package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.collection.CollectionManager
import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.common.util.SpaceMarineComparator
import java.util.*
import kotlin.streams.toList

class FilterLessThanMeleeWeaponCommand(
    val collectionManager: CollectionManager<SpaceMarine>
) : AbstractCommand(
    "filter_less_than_melee_weapon",
    "Выводит все элементы, оружие ближнего боя у которых меньше заданного.",
    "meleeWeapon",
    FOR_SERVER_AND_LOGGED_IN_CLIENT
) {
    override fun invoke(request: Request): Response? {
        val list = collectionManager
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