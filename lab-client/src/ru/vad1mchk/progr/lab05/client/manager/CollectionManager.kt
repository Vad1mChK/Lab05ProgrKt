package ru.vad1mchk.progr.lab05.client.manager

interface CollectionManager<E> {
    fun info(): String

    fun show()

    fun add(element: E)

    fun updateById(id: Int, element: E)

    fun removeById(id: Int)

    fun clear()

    fun addIfMin(element: E)

    fun removeGreater(element: E)
}