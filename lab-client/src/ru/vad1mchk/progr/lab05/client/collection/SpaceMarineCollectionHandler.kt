package ru.vad1mchk.progr.lab05.client.collection

import ru.vad1mchk.progr.lab05.client.datatypes.SpaceMarine
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Class to handle the collection of SpaceMarines specifically
 */
class SpaceMarineCollectionHandler : CollectionHandler<SpaceMarine> {
    /**
     * Collection of SpaceMarines
     */
    override val collection: LinkedList<SpaceMarine>

    /**
     * Set of unique identifiers already existing in collection
     */
    private val uniqueIds: HashSet<Int>

    /**
     * Date of collection initialization
     */
    private val initializationDate: LocalDate

    init {
        collection = LinkedList()
        uniqueIds = HashSet()
        initializationDate = LocalDate.now()
    }

    override fun sort() {
        Collections.sort(collection)
    }

    override fun info(): String {
        return String.format(
            "Collection of SpaceMarine\n\tType: %s\n\tInitialization date: %s\n\tSize: %d elements\n\tIDs taken: %s",
            collection.javaClass.simpleName,
            initializationDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
            collection.size,
            uniqueIds
        )
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
        var idx = 0
        for (marine in collection) {
            if (marine.id == id) {
                newMarine.id = id
                collection[idx] = newMarine
                return
            }
            idx++
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
}