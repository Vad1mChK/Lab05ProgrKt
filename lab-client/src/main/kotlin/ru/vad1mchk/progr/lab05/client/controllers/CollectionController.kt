package ru.vad1mchk.progr.lab05.client.controllers;

import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import java.util.*

class CollectionController {
    private var spaceMarines: LinkedList<SpaceMarine> = LinkedList()

    fun updateCollection(newCollection: LinkedList<SpaceMarine>) {
        spaceMarines = newCollection

    }
}
