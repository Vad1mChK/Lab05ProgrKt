package ru.vad1mchk.progr.lab05.common.communication

import ru.vad1mchk.progr.lab05.common.datatypes.MeleeWeapon
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import java.io.Serializable

/**
 * Data class representing a request that can be sent to the server and executed.
 * @property commandName Name of the command to execute.
 * @property spaceMarineArgument Space marine argument of type [SpaceMarine], if any.
 * @property idArgument ID argument of type [Int], if any.
 * @property heartCountArgument Heart count argument of type [Long], if any.
 * @property meleeWeaponArgument Melee weapon argument of type [MeleeWeapon], if any.
 * @property isServerRequest If this request was created by the server.
 */
data class Request(
    val commandName: String,
    val spaceMarineArgument: SpaceMarine? = null,
    val idArgument: Int? = null,
    val heartCountArgument: Long? = null,
    val meleeWeaponArgument: MeleeWeapon? = null,
    var isServerRequest: Boolean = false
) : Serializable {
    companion object {
        const val serialVersionUID = 2_147_483_647L
    }
}