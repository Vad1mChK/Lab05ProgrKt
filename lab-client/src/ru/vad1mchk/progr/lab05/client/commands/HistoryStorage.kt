package ru.vad1mchk.progr.lab05.client.commands

import java.util.*

/**
 * Class to store terminal history
 */
class HistoryStorage {
    /**
     * stack to store command names
     */
    private val stackOfTypes = Stack<Command.CommandType>()

    /**
     * Adds the name of specified command into the stack
     * @param command command to add
     */
    fun addCommand(command: Command) {
        stackOfTypes.push(command.commandType)
    }

    /**
     * Clears the history storage
     */
    fun empty() {
        stackOfTypes.empty()
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val that = o as HistoryStorage
        return stackOfTypes == that.stackOfTypes
    }

    override fun hashCode(): Int {
        return stackOfTypes.hashCode() xor Int.MAX_VALUE
    }

    override fun toString(): String {
        return java.lang.String.join("\n", stackOfTypes as Stack<String>)
    }

    companion object {
        /**
         * Length of history (last printed commands count), const
         */
        const val HISTORY_LENGTH = 12
    }
}