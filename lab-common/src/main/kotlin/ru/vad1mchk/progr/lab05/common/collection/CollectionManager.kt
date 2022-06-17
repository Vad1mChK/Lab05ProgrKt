package ru.vad1mchk.progr.lab05.common.collection

import ru.vad1mchk.progr.lab05.common.datatypes.User
import ru.vad1mchk.progr.lab05.common.exceptions.IdentifierCollisionException
import ru.vad1mchk.progr.lab05.common.exceptions.IdentifierNotExistsException
import java.io.Serializable
import java.time.LocalDate
import java.util.stream.Stream

/**
 * Interface for collection managers that work with collections of type
 * [E].
 *
 * @param E Element type.
 */
interface CollectionManager<E : Serializable> {
    /**
     * Gets an element by the specified ID if it exists in the
     * collection.
     *
     * @param id ID of the element to get.
     * @return The element of this collection if it was found.
     * @throws IdentifierNotExistsException if the element by this ID
     *     was not found.
     */
    @Throws(IdentifierNotExistsException::class)
    operator fun get(id: Int): E

    /**
     * Replaces all the elements of the collection with new ones, adding
     * them by their IDs.
     *
     * @param elements Elements to put instead of the old ones.
     */
    @Throws(IdentifierCollisionException::class)
    fun setAll(elements: Collection<E>)

    /**
     * Generates a brand-new ID for the new element to add.
     *
     * @return The newly generated ID.
     * @throws IdentifierCollisionException if the collection is already
     *     full.
     */
    @Throws(IdentifierCollisionException::class)
    fun generateID(): Int

    /**
     * Adds a new element to the collection, generating a new ID for it.
     *
     * @param element Element to add.
     * @throws IdentifierCollisionException if the collection is already
     *     full.
     */
    @Throws(IdentifierCollisionException::class)
    fun add(element: E)

    /**
     * Adds a new element to the collection, keeping its ID intact and
     * failing if there is already an element with such ID.
     *
     * @param element Element to add.
     * @throws IdentifierCollisionException if this ID is already
     *     occupied.
     */
    @Throws(IdentifierCollisionException::class)
    fun addPreservingID(element: E)

    /**
     * Updates the element of this collection with a new one.
     *
     * @param id ID to update the element by.
     * @param newElement New element to replace the old one with.
     */
    @Throws(IdentifierNotExistsException::class)
    fun update(id: Int, newElement: E)

    /**
     * Removes an element with the specified ID from the collection.
     *
     * @param id ID to remove the element from.
     * @throws IdentifierNotExistsException if the element was not found
     *     by ID.
     */
    @Throws(IdentifierNotExistsException::class)
    fun removeById(id: Int)

    /**
     * Removes all elements from the collection. If value passed as [user] is `null`, all elements are removed,
     * if not `null`, only the elements that belong to [user] are removed.
     * @param user User that owns the elements that should be deleted.
     */
    fun clear(user: User? = null)

    /**
     * Returns the collection used by this manager.
     * @return The collection used by this manager.
     */
    fun collection(): Collection<E>

    /**
     * Returns the initialization date of this collection.
     * @return the initialization date of this collection.
     */
    fun initializationDate(): LocalDate

    /**
     * Streams the collection used by this manager.
     *
     * @return The stream of elements stored in this collection.
     */
    fun stream(): Stream<E>

    /**
     * Calculates the size of the collection.
     *
     * @return The size of the collection stored in this manager.
     */
    fun size(): Int
}