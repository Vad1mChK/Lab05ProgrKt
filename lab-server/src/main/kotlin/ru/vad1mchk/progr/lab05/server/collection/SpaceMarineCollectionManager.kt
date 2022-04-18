package ru.vad1mchk.progr.lab05.server.collection

import ru.vad1mchk.progr.lab05.common.datatypes.MeleeWeapon
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.common.exceptions.IdentifierCollisionException
import ru.vad1mchk.progr.lab05.common.exceptions.IdentifierNotExistsException
import ru.vad1mchk.progr.lab05.common.io.OutputManager
import ru.vad1mchk.progr.lab05.common.messages.Messages
import ru.vad1mchk.progr.lab05.common.util.DateFormatter
import java.time.LocalDate
import java.util.*
import kotlin.streams.toList

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
        return LinkedList(collection.stream().filter(function).toList())
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
        if (collection.stream().anyMatch { it <= newElement }) return
        collection.add(newElement)
    }

    override fun removeGreater(comparisonElement: SpaceMarine) {
        LinkedList(collection).stream().filter { it > comparisonElement }.forEach { removeById(it.id) }
    }

    /**
     * Prints all elements that have the meleeWeapon field less than the specified [meleeWeapon].
     * @param meleeWeapon [MeleeWeapon] to compare with.
     */
    fun filterLessThanMeleeWeapon(meleeWeapon: MeleeWeapon) {
        collection.stream().filter { it.meleeWeapon?.let {mw -> mw < meleeWeapon} ?: false }.forEach { println(it.toCoolerString()) }
    }

    /**
     * Prints all elements that have the heartCount field greater than the specified [heartCount].
     * @param heartCount heart count value to compare with.
     */
    fun filterGreaterThanHeartCount(heartCount: Long) {
        collection.stream().filter { it.heartCount > heartCount }.forEach { println(it.toCoolerString()) }
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
        SpaceMarineCollectionManager.initializationDate = initializationDate
    }
}