package ru.vad1mchk.progr.lab05.client.collection

import ru.vad1mchk.progr.lab05.client.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.client.messages.Messages
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Class to handle the collection of SpaceMarines specifically
 */
class SpaceMarineCollectionHandler(
    /**
     * Collection of SpaceMarines
     */
    val collection: LinkedList<SpaceMarine>
) : CollectionHandler<SpaceMarine> {

    /**
     * Set of unique identifiers already existing in collection
     */
    private val uniqueIds: HashSet<Int> = HashSet()

    /**
     * Date of collection initialization
     */
    private val initializationDate: LocalDate = LocalDate.now()

    override fun sort() {
        collection.sort()
    }

    override fun info(): String {
        val uniqueIdsAsStrings: ArrayList<String> = ArrayList()
        for (id in uniqueIds) {
            uniqueIdsAsStrings.add(id.toString())
        }
        return String.format(
            Messages.get("infoFormatString"),
            collection.javaClass.simpleName,
            initializationDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
            collection.size,
            if (collection.isEmpty()) "none" else java.lang.String.join(", ", uniqueIdsAsStrings)
        )
    }

    override fun show() {
        for (marine in collection) {
            println(marine)
        }
    }

    override fun add(marine: SpaceMarine) {
        val id = generateId()
        if (hasId(id)) {
            throw InternalError("Attempt to add marine by an existing index")
        }
        uniqueIds.add(id)
        marine.id = id
        collection.add(marine)
    }

    override fun generateId(): Int {
        var id = 0
        do {
            id++
        } while (hasId(id))
        return id
    }

    override fun hasId(id: Int): Boolean {
        return uniqueIds.contains(id)
    }

    override fun removeById(id: Int) {
        for (marine in collection) {
            if (marine.id == id) {
                collection.remove(marine)
                uniqueIds.remove(id)
                return
            }
        }
    }

    override fun updateById(id: Int, newMarine: SpaceMarine) {
        for ((idx, marine) in collection.withIndex()) {
            if (marine.id == id) {
                newMarine.id = id
                collection[idx] = newMarine
                return
            }
        }
    }

    override fun size(): Int {
        return collection.size
    }

    override fun addIfMin(marine: SpaceMarine) {
        sort()
        if (marine.compareTo(collection.first) < 0) {
            add(marine)
        }
    }

    override fun removeGreater(marineToCompareWith: SpaceMarine) {
        for (marine in LinkedList(collection)) {
            if (marine.compareTo(marineToCompareWith) > 0) {
                removeById(marine.id)
            }
        }
    }

    override fun printFieldDescendingHealth() {
        val healthList = LinkedList<Double?>()
        for (marine in collection) {
            healthList.add(marine.health)
        }
        healthList.sortWith(Comparator { left, right -> (right!!).compareTo(left!!) })
        println(healthList)
    }

    override fun clear() {
        collection.clear()
    }
}