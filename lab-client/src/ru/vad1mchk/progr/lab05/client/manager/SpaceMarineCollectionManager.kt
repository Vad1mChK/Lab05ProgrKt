package ru.vad1mchk.progr.lab05.client.manager

import ru.vad1mchk.progr.lab05.client.datatypes.SpaceMarine
import java.util.*

object SpaceMarineCollectionManager : CollectionManager<SpaceMarine> {
    private val collection: LinkedList<SpaceMarine> = LinkedList()

    override fun info(): String {
        TODO("Not yet implemented")
    }

    override fun show() {
        TODO("Not yet implemented")
    }

    override fun add(element: SpaceMarine) {
        TODO("Not yet implemented")
    }

    override fun updateById(id: Int, element: SpaceMarine) {
        TODO("Not yet implemented")
    }

    override fun removeById(id: Int) {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun addIfMin(element: SpaceMarine) {
        TODO("Not yet implemented")
    }

    override fun removeGreater(element: SpaceMarine) {
        TODO("Not yet implemented")
    }
}