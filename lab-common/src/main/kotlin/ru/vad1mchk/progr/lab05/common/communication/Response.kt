package ru.vad1mchk.progr.lab05.common.communication

import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import java.io.Serializable
import java.util.LinkedList

data class Response(
    val stringMessage: String,
    val spaceMarines: LinkedList<SpaceMarine>? = null
) : Serializable {
    companion object {
        const val serialVersionUID = 2_147_483_648L
    }
}