package ru.vad1mchk.progr.lab05.common.communication

import ru.vad1mchk.progr.lab05.common.datatypes.MeleeWeapon
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import java.io.Serializable

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