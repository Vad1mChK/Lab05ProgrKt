package ru.vad1mchk.progr.lab05.common.connection

import ru.vad1mchk.progr.lab05.common.datatypes.MeleeWeapon
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import java.io.Serializable

data class Request (
    val commandName: String,
    val spaceMarine: SpaceMarine? = null,
    val idArgument: Int? = null,
    val heartCountArgument: Long? = null,
    val meleeWeaponArgument: MeleeWeapon? = null,
    var clientInformation: String? = null,
) : Serializable {
    companion object {
        private val serialVersionUID = 42069671L
    }
}