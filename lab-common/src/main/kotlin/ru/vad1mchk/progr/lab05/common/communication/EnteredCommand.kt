package ru.vad1mchk.progr.lab05.common.communication

import ru.vad1mchk.progr.lab05.common.util.Varargparse

/**
 * Class to temporarily store the command and its arguments before
 * creating a request.
 *
 * @property name Name of the command.
 * @property arguments Arguments of the command.
 */
data class EnteredCommand(
    val name: String,
    val arguments: ArrayList<String>
) {
    companion object {
        /**
         * Creates an instance of [EnteredCommand] from the entered
         * string.
         *
         * @param commandString String to parse the command from.
         * @return The command parsed from the specified string.
         */
        @JvmStatic
        fun fromString(commandString: String): EnteredCommand? {
            val splitResult = Varargparse.splitString(commandString.trim())
            return if (splitResult.isEmpty()) {
                null
            } else {
                EnteredCommand(splitResult.removeFirst(), splitResult)
            }
        }
    }
}