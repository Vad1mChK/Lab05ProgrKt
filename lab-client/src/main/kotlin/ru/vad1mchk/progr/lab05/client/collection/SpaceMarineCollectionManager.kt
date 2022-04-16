package ru.vad1mchk.progr.lab05.client.collection

import ru.vad1mchk.progr.lab05.client.datatypes.MeleeWeapon
import ru.vad1mchk.progr.lab05.client.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.client.exceptions.IdentifierCollisionException
import ru.vad1mchk.progr.lab05.client.exceptions.IdentifierNotExistsException
import ru.vad1mchk.progr.lab05.client.io.OutputManager
import ru.vad1mchk.progr.lab05.client.messages.Messages
import ru.vad1mchk.progr.lab05.client.util.DateFormatter
import java.time.LocalDate
import java.util.*

/**
 * Implementation of [CollectionManager] designed to work with [SpaceMarine] specifically.
 */
object SpaceMarineCollectionManager : CollectionManager<SpaceMarine> {
    private val collection = LinkedList<SpaceMarine>()
    private val uniqueIds = HashSet<Int>()
    lateinit var initializationDate: LocalDate
        private set

    override fun generateId(): Int {
        var newId: Int
        do {
            newId = (SpaceMarine.MIN_ID..Int.MAX_VALUE).random()
        } while (newId in uniqueIds)
        return newId
    }

    override fun sort() {
        collection.sort()
    }

    override fun filteredCollection(function: (SpaceMarine) -> Boolean): LinkedList<SpaceMarine> {
        return LinkedList(collection.filter(function))
    }

    override fun iterator(): MutableIterator<SpaceMarine> {
        return collection.iterator()
    }

    override fun contains(element: SpaceMarine): Boolean {
        return element in collection
    }

    override fun info() {
        OutputManager.sayInfo(
            Messages.infoString,
            collection.javaClass.simpleName,
            SpaceMarine::class.simpleName,
            collection.size,
            DateFormatter.format(initializationDate)
        )
    }

    override fun show() {
        sort()
        for (element in collection) {
            OutputManager.say(element.toCoolerString())
        }
    }

    override fun add(newElement: SpaceMarine) {
        generateId().also {
            newElement.id = it
            uniqueIds.add(it)
        }
        collection.add(newElement)
    }

    override fun addById(id: Int, newElement: SpaceMarine) {
        newElement.id = id
        if (id in uniqueIds) {
            throw IdentifierCollisionException(String.format(Messages.exceptionIdentifierCollision, id))
        }
        collection.add(newElement)
        uniqueIds.add(id)
    }

    override fun updateById(id: Int, newElement: SpaceMarine) {
        removeById(id)
        addById(id, newElement)
    }

    override fun removeById(id: Int) {
        if (id !in uniqueIds) {
            throw IdentifierNotExistsException(String.format(Messages.exceptionIdentifierNotExists, id))
        }
        for (element in collection) {
            if (element.id == id) {
                collection.remove(element)
                break
            }
        }
        uniqueIds.remove(id)
    }

    override fun clear() {
        collection.clear()
        uniqueIds.clear()
    }

    override fun addIfMin(newElement: SpaceMarine) {
        for (element in collection) {
            if (element <= newElement) {
                return
            }
        }
        add(newElement)
    }

    override fun removeGreater(comparisonElement: SpaceMarine) {
        for (element in LinkedList(collection)) {
            if (element > comparisonElement) {
                collection.remove(element)
            }
        }
    }

    /**
     * Prints all elements that have the meleeWeapon field less than the specified [meleeWeapon].
     * @param meleeWeapon [MeleeWeapon] to compare with.
     */
    fun filterLessThanMeleeWeapon(meleeWeapon: MeleeWeapon) {
        for (element in filteredCollection {
            it.meleeWeapon?.let { weapon ->
                weapon < meleeWeapon
            } ?: false
        }) {
            OutputManager.say(element.toCoolerString())
        }
    }

    /**
     * Prints all elements that have the heartCount field greater than the specified [heartCount].
     * @param heartCount heart count value to compare with.
     */
    fun filterGreaterThanHeartCount(heartCount: Long) {
        for (element in filteredCollection { it.heartCount > heartCount }) {
            OutputManager.say(element.toCoolerString())
        }
    }

    /**
     * Prints health values of all elements sorted descending.
     */
    fun printFieldDescendingHealth() {
        for (health in collection.map { it.health }.sortedDescending()) {
            OutputManager.say(health.toString())
        }
    }

    fun initialize(initializationDate: LocalDate) {
        this.initializationDate = initializationDate
    }
}