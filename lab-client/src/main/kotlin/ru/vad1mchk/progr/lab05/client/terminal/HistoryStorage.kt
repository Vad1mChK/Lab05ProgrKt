package ru.vad1mchk.progr.lab05.client.terminal

import ru.vad1mchk.progr.lab05.client.io.OutputManager
import ru.vad1mchk.progr.lab05.client.messages.Messages
import ru.vad1mchk.progr.lab05.client.terminal.HistoryStorage.CAPACITY
import kotlin.math.min

/**
 * Class to store a limited amount of history items (not more than [CAPACITY]) and return them if needed.
 */
object HistoryStorage {
    /**
     * Maximum amount of items that the [HistoryStorage] can store at a time.
     */
    private const val CAPACITY = 12

    private val dequeOfNames = ArrayDeque<String>()

    /**
     * Adds the name of this command to the storage, removing an element at the beginning if the size of the storage
     * exceeds [CAPACITY].
     * @param commandName Name of the command to add.
     */
    fun add(commandName: String) {
        if (dequeOfNames.size == CAPACITY) {
            dequeOfNames.removeFirst()
        }
        dequeOfNames.add(commandName)
    }

    /**
     * Prints out the elements of the history storage.
     */
    fun print() {
        OutputManager.sayInfo(Messages.historyString, min(CAPACITY, dequeOfNames.size))
        for (element in dequeOfNames) {
            OutputManager.say(element)
        }
    }
}