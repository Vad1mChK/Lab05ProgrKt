package ru.vad1mchk.progr.lab05.common.communication

import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.common.datatypes.User
import java.io.Serializable
import java.util.*

/**
 * Data class representing a response that is returned after command execution.
 * @property stringMessage Message to print.
 * @property spaceMarines List of space marines to return, if any.
 */
data class Response(
    val stringMessage: String,
    val spaceMarines: LinkedList<SpaceMarine>? = null,
    val user: User? = null,
    var notification: Boolean? = null
) : Serializable {
    companion object {
        const val serialVersionUID = 2_147_483_648L
    }
}