package ru.vad1mchk.progr.lab05.server

import ru.vad1mchk.progr.lab05.common.datatypes.Coordinates
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.server.collection.SpaceMarineCollectionManager
import java.time.LocalDate

fun main(args: Array<String>) {
    SpaceMarineCollectionManager.add(
        SpaceMarine("Phoenix", Coordinates(837, 888.0f), LocalDate.now(), 0.5, 3, true, null, null ),
    )
    SpaceMarineCollectionManager.add(
        SpaceMarine("Miles", Coordinates(837, 888.0f), LocalDate.now(), 0.5, 2, true, null, null ),
    )
    SpaceMarineCollectionManager.add(
        SpaceMarine("Godot", Coordinates(837, 888.0f), LocalDate.now(), 0.5, 1, true, null, null ),
    )
    SpaceMarineCollectionManager.add(
        SpaceMarine("Maya", Coordinates(837, 888.0f), LocalDate.now(), 0.4, 2, true, null, null ),
    )
    SpaceMarineCollectionManager.removeGreater(SpaceMarine("Miles", Coordinates(837, 888.0f), LocalDate.now(), 0.5, 1, true, null, null ))
    SpaceMarineCollectionManager.show()
}