package ru.vad1mchk.progr.lab05.client.terminal

object HistoryStorage {
    const val CAPACITY = 12

    private val dequeOfNames = ArrayDeque<String>()

    fun add(commandName: String) {
        dequeOfNames.add(commandName)
        cap()
    }

    private fun cap() {
        if (dequeOfNames.size <= CAPACITY) return
        dequeOfNames.removeFirst()
    }

    fun print() {
        for (name in dequeOfNames) {
            println(name)
        }
    }
}