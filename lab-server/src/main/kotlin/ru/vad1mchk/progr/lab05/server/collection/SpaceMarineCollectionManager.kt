package ru.vad1mchk.progr.lab05.server.collection

import ru.vad1mchk.progr.lab05.common.collection.CollectionManager
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.common.exceptions.IdentifierCollisionException
import ru.vad1mchk.progr.lab05.common.exceptions.IdentifierNotExistsException
import ru.vad1mchk.progr.lab05.common.util.SpaceMarineComparator
import ru.vad1mchk.progr.lab05.common.util.ValueFormatter
import ru.vad1mchk.progr.lab05.server.util.Configuration
import java.time.LocalDate
import java.util.*
import java.util.stream.Collectors

/**
 * TODO
 *
 */
class SpaceMarineCollectionManager(private val comparisonLocale: Locale) : CollectionManager<SpaceMarine> {
    private val collection = LinkedList<SpaceMarine>()
    private val uniqueIds = HashSet<Int>()
    private var initializationDate: LocalDate = LocalDate.now()
    private val comparator = SpaceMarineComparator(comparisonLocale)
    private val stringResources = Configuration.STRING_RESOURCES

    override fun generateId(): Int {
        var newId: Int = SpaceMarine.MIN_ID
        while (newId in uniqueIds) {
            newId++
        }
        return newId
    }

    override fun collection(): LinkedList<SpaceMarine> {
        return LinkedList(collection.sortedWith(comparator))
    }

    override fun filteredCollection(function: (SpaceMarine) -> Boolean): LinkedList<SpaceMarine> {
        return LinkedList(collection.stream().filter { function(it) }.collect(Collectors.toList()))
    }

    override fun iterator(): MutableIterator<SpaceMarine> {
        return collection.iterator()
    }

    override fun contains(element: SpaceMarine): Boolean {
        return element in collection
    }

    override fun info(): String {
        return String.format(
            stringResources.getString("collection info"),
            collection.javaClass.simpleName,
            SpaceMarine::class.simpleName,
            size(),
            ValueFormatter(Configuration.currentLocale).formatLocalDate(initializationDate)
        )
    }

    override fun add(newElement: SpaceMarine) {
        generateId().also {
            newElement.id = it
            uniqueIds.add(it)
        }
        collection.add(newElement)
    }

    override fun addById(id: Int, newElement: SpaceMarine) {
        if (id in uniqueIds) {
            throw IdentifierCollisionException(stringResources.getString("IdentifierCollisionException").format(id))
        }
        uniqueIds.add(id)
        collection.add(newElement.also { it.id = id })
    }

    override fun updateById(id: Int, newElement: SpaceMarine) {
        if (id !in uniqueIds) {
            throw IdentifierNotExistsException(
                stringResources.getString("IdentifierNotExistsException update").format(id)
            )
        }
        collection.removeIf { it.id == id }
        collection.add(newElement.also { it.id = id })
    }

    override fun removeById(id: Int) {
        if (id !in uniqueIds) {
            throw IdentifierNotExistsException(
                stringResources.getString("IdentifierNotExistsException remove").format(id)
            )
        }
        collection.removeIf { it.id == id }
        uniqueIds.remove(id)
    }

    override fun clear() {
        collection.clear()
        uniqueIds.clear()
    }

    override fun addIfMin(newElement: SpaceMarine) {
        if (collection.stream().anyMatch { comparator.compare(it, newElement) < 0 }) return
        collection.add(newElement)
    }

    override fun removeGreater(comparisonElement: SpaceMarine) {
        collection.removeIf { comparator.compare(it, comparisonElement) > 0 }
    }

    override fun size(): Int {
        return collection.size
    }

    /**
     * Sets a new initialization date for this collection when reading it from a file anew.
     * @param initializationDate Initialization date to set.
     */
    fun initializeWithDate(initializationDate: LocalDate) {
        this.initializationDate = initializationDate
    }
}