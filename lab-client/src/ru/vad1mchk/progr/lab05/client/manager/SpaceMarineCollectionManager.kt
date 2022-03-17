package ru.vad1mchk.progr.lab05.client.manager

import ru.vad1mchk.progr.lab05.client.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.client.exceptions.IDCollisionException
import ru.vad1mchk.progr.lab05.client.messages.Messages
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

object SpaceMarineCollectionManager : CollectionManager<SpaceMarine> {
    private val collection: LinkedList<SpaceMarine> = LinkedList()
    private val uniqueIds = HashSet<Int>()
    lateinit var initializationDate: LocalDate

    private fun hasId(id: Int): Boolean {
        return uniqueIds.contains(id)
    }

    override fun generateId(): Int {
        var id: Int
        do {
            id = (SpaceMarine.MIN_ID..Int.MAX_VALUE).random()
        } while (id in uniqueIds)
        return id
    }

    override fun info(): String {
        return String.format(
            Messages["infoFormatString"],
            collection.javaClass.simpleName,
            SpaceMarine::class.java.simpleName,
            collection.size,
            initializationDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        )
    }

    override fun show() {
        collection.sort()
        for (element in collection) {
            println(element)
        }
    }

    override fun addWithId(id: Int, newElement: SpaceMarine) {
        newElement.id = id
        if (hasId(id)) {
            throw IDCollisionException(String.format(Messages["errorIDCollision"], id))
        } else {
            collection.add(newElement)
            uniqueIds.add(id)
        }
    }

    override fun add(element: SpaceMarine) {
        addWithId(generateId(), element)
    }

    override fun updateById(id: Int, newElement: SpaceMarine) {
        for ((idx, element) in collection.withIndex()) {
            if (element.id == id) {
                newElement.id = id
                collection[idx] = newElement
                return
            }
        }
    }

    override fun removeById(id: Int) {
        for (element in collection) {
            if (element.id == id) {
                collection.remove(element)
                uniqueIds.remove(id)
                return
            }
        }
    }

    override fun clear() {
        collection.clear()
        uniqueIds.clear()
    }

    override fun addIfMin(element: SpaceMarine) {
        for (oldElement in collection) {
            if (oldElement < element) {
                return
            }
        }
        add(element)
    }

    override fun removeGreater(element: SpaceMarine) {
        for (oldElement in collection) {
            if (oldElement > element) {
                removeById(oldElement.id)
            }
        }
    }
}