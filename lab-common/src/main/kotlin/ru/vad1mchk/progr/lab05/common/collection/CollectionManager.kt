package ru.vad1mchk.progr.lab05.common.collection

import ru.vad1mchk.progr.lab05.common.exceptions.IdentifierCollisionException
import ru.vad1mchk.progr.lab05.common.exceptions.IdentifierNotExistsException
import java.util.*

/**
 * Base interface for a manager that works with a collection of type [T].
 * @param T Element type.
 */
interface CollectionManager<T : Any> {

    /**
     * Generates next unique identifier.
     * @return A new unique id.
     */
    fun generateId(): Int

    /**
     * Returns all elements of the collection.
     * @return [LinkedList] of all elements.
     */
    fun collection(): LinkedList<T>

    /**
     * Filters out all the elements that match the condition using the lambda function/predicate.
     * @param function Lambda function.
     * @return [LinkedList] of elements that match the condition.
     */
    fun filteredCollection(function: (T) -> Boolean): LinkedList<T>

    /**
     * Iterator that allows you to iterate through collection of [T].
     * @return The collection iterator.
     */
    operator fun iterator(): MutableIterator<T>

    /**
     * Checks if [element] is in the collection.
     * @return `true` if [element] is in collection, else `false`
     */
    operator fun contains(element: T): Boolean

    /**
     * Prints the following information about the collection: collection type, elements type, size and initialization
     * date.
     * @return Information string.
     */
    fun info(): String

    /**
     * Add a [newElement] with the newly-generated identifier to the collection.
     * @param newElement Element to add.
     */
    fun add(newElement: T)

    /**
     * Adds the [newElement] with the specified [id] to the collection.
     * @param id Identifier of the new element.
     * @param newElement Element to add.
     * @throws IdentifierCollisionException when attempting to add a new element by the already existing identifier.
     */
    @Throws(IdentifierCollisionException::class)
    fun addById(id: Int, newElement: T)

    /**
     * Replaces the element with the specified [id] with [newElement].
     * @param id Identifier of the element.
     * @param newElement Element to replace the old element with.
     * @throws IdentifierNotExistsException when attempting to update an element by a non-existent identifier.
     */
    @Throws(IdentifierNotExistsException::class)
    fun updateById(id: Int, newElement: T)

    /**
     * Deletes the element with the specified [id].
     * @param id Identifier of the element.
     * @throws IdentifierNotExistsException when attempting to delete an element by a non-existent identifier.
     */
    @Throws(IdentifierNotExistsException::class)
    fun removeById(id: Int)

    /**
     * Completely clears the collection.
     */
    fun clear()

    /**
     * Adds a [newElement] with the newly-generated identifier to the collection if it is less than all others.
     * @param newElement Element to add.
     */
    fun addIfMin(newElement: T)

    /**
     * Removes all elements greater than [comparisonElement]
     * from the collection.
     * @param comparisonElement Element to compare with.
     */
    fun removeGreater(comparisonElement: T)

    /**
     * Calculates the size of the collection.
     * @return The size of the collection stored in this manager.
     */
    fun size(): Int
}