package ru.vad1mchk.progr.lab05.client.collection

import java.util.*

/**
 * Interface to be implemented by handler of LinkedList collection
 *
 * @param <T> type of elements to store in a collection
</T> */
interface CollectionHandler<T> {
    /**
     * Sort the collection according to the comparison rules
     */
    fun sort()

    /**
     * Getter of collection
     *
     * @return collection
     */
    val collection: LinkedList<T>

    /**
     * Informs the user about collection type, initialization date, element count
     *
     * @return information string
     */
    fun info(): String

    /**
     * Adds element to collection
     *
     * @param element element to add
     */
    fun add(element: T)

    /**
     * Generates a new ID that is unique to the collection
     *
     * @return newly generated ID
     */
    fun generateId(): Int

    /**
     * Checks if the collection has an element with the specified ID
     *
     * @param id ID
     * @return true if element with this ID exists, false otherwise
     */
    fun hasId(id: Int): Boolean

    /**
     * Removes element with the specified ID
     *
     * @param id ID
     */
    fun removeById(id: Int)

    /**
     * Updates element with the specified ID with a new element
     *
     * @param id         ID
     * @param newElement new element to replace the old one
     */
    fun updateById(id: Int, newElement: T)

    /**
     * Calculates size of the collection
     *
     * @return size of the collection
     */
    fun size(): Int

    /**
     * Adds element if it is less than any other
     *
     * @param element element to add
     */
    fun addIfMin(element: T)

    /**
     * Removes all elements greater than given
     *
     * @param element element to compare with
     */
    fun removeGreater(element: T)

    /**
     * Prints all health values in descending order
     */
    fun printFieldDescendingHealth()
}