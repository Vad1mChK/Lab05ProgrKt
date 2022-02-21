package ru.vad1mchk.progr.lab05.client.commands

import java.lang.Integer.min
import java.util.*
import kotlin.collections.ArrayList

/**
 * Class to store terminal history
 */
class HistoryStorage {
    /**
     * stack to store command names
     */
    private val arrayListOfTypes = ArrayList<Command.CommandType>()

    /**
     * Adds the name of specified command into the stack
     * @param command command to add
     */
    fun addCommand(command: Command) {
        arrayListOfTypes.add(command.commandType)
        if(arrayListOfTypes.size > HISTORY_LENGTH) {
            arrayListOfTypes.removeAt(0)
        }
    }

    /**
     * Clears the history storage
     */
    fun empty() {
        arrayListOfTypes.clear()
    }

    fun print() {
        if(arrayListOfTypes.isNotEmpty()) {
            println(this)
        }
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val that = o as HistoryStorage
        return arrayListOfTypes == that.arrayListOfTypes
    }

    override fun hashCode(): Int {
        return arrayListOfTypes.hashCode() xor Int.MAX_VALUE
    }

    override fun toString(): String {
        val arrayListOfStrings: ArrayList<String> = ArrayList()
        for (i in (0 until min(HISTORY_LENGTH, arrayListOfTypes.size))) {
            arrayListOfStrings.add(arrayListOfTypes[i].cmdName)
        }
        return java.lang.String.join("\n", arrayListOfStrings)
    }

    companion object {
        /**
         * Length of history (last printed commands count), const
         */
        const val HISTORY_LENGTH = 12
    }
}