package ru.vad1mchk.progr.lab05.server.collection

import ru.vad1mchk.progr.lab05.common.collection.CollectionManager
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.common.datatypes.User
import ru.vad1mchk.progr.lab05.common.exceptions.IdentifierCollisionException
import ru.vad1mchk.progr.lab05.common.exceptions.IdentifierNotExistsException
import java.security.SecureRandom
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.stream.Stream

/**
 * Implementation of [CollectionManager] used to work with instances of
 * [SpaceMarine] specifically.
 */
class SpaceMarineCollectionManager : CollectionManager<SpaceMarine> {
    companion object {
        const val MIN_ID = 1
    }

    private val collection = Collections.synchronizedList(LinkedList<SpaceMarine>())
    private val uniqueIds = HashSet<Int>()
    private val initializationDate = LocalDate.now()
    private val randomGenerator = SecureRandom()

    override fun get(id: Int): SpaceMarine {
        return try {
            if (id in uniqueIds) {
                collection.first { it.id == id }
            } else {
                throw IdentifierNotExistsException("Ошибка: не удалось найти элемент с ID #$id.")
            }
        } catch (e: NoSuchElementException) {
            throw IdentifierNotExistsException("Ошибка: не удалось найти элемент с ID #$id.", e)
        }
    }

    override fun setAll(elements: Collection<SpaceMarine>) {
        clear()
        elements.forEach {
            uniqueIds.add(it.id)
            collection.add(it)
        }
    }

    override fun generateID(): Int {
        if (uniqueIds.size == Int.MAX_VALUE) {
            throw IdentifierCollisionException("Ошибка: достигнут максимальный размер коллекции.")
        }
        (uniqueIds.maxOrNull() ?: MIN_ID).also {
            if (it != Int.MAX_VALUE) return it + 1
        }
        var id: Int
        do {
            id = randomGenerator.also {
                it.setSeed(Instant.now().epochSecond)
            }.nextInt(Int.MAX_VALUE) + MIN_ID
        } while (id in uniqueIds)
        return id
    }

    override fun add(element: SpaceMarine) {
        val id = generateID().also { element.id = it }
        uniqueIds.add(id)
        collection.add(element)
    }

    override fun addPreservingID(element: SpaceMarine) {
        val id = element.id
        if (id in uniqueIds) {
            throw IdentifierCollisionException("Ошибка: элемент с ID #$id уже существует.")
        }
        uniqueIds.add(id)
        collection.add(element)
    }

    override fun update(id: Int, newElement: SpaceMarine) {
        newElement.id = id
        if (collection.stream().noneMatch { it.id == id }) {
            throw IdentifierNotExistsException("Ошибка: не удалось найти элемент с ID #$id.")
        }
        collection.removeIf { it.id == id }
        collection.add(newElement)
    }

    override fun removeById(id: Int) {
        if (collection.stream().noneMatch { it.id == id }) {
            throw IdentifierNotExistsException("Ошибка: не удалось найти элемент с ID #$id.")
        }
        collection.removeIf { it.id == id }
    }

    override fun clear(user: User?) {
        if (user == null) {
            collection.clear()
        } else {
            collection.removeIf { it.creatorName == user.userName }
        }
    }

    override fun stream(): Stream<SpaceMarine> {
        return collection.stream()
    }

    override fun collection(): MutableList<SpaceMarine> {
        return collection
    }

    override fun initializationDate(): LocalDate {
        return initializationDate
    }

    override fun size(): Int {
        return collection.size
    }
}