package ru.vad1mchk.progr.lab05.common.connection

import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import java.io.Serializable

/**
 * Command message that is sent from the client to the server to request actions.
 * @property commandName Name of the command to execute.
 * @property commandStringArguments Array of string arguments of the command.
 * @property commandAnyArgument The object argument of the command.
 */
class CommandMessage(
    val commandName: String,
    val commandStringArguments: Array<String>?,
    val commandAnyArgument: Serializable?
): Serializable {
    /**
     * Function to obtain the serialized element.
     * @return The serialized [SpaceMarine] instance if any was passed as an argument of this command message, else
     * `null`.
     */
    fun getSpaceMarine(): SpaceMarine? {
        return commandAnyArgument as SpaceMarine?
    }
}