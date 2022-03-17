package ru.vad1mchk.progr.lab05.client.manager

interface CollectionManager<E> {
    fun generateId(): Int

    fun info(): String

    fun show()

    fun addWithId(id: Int, newElement: E)

    fun add(element: E)

    fun updateById(id: Int, newElement: E)

    fun removeById(id: Int)

    fun clear()

    fun addIfMin(element: E)

    fun removeGreater(element: E)
}