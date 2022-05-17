package ru.vad1mchk.progr.lab05.common.datatypes

import java.io.Serializable
import java.time.LocalDate

data class SpaceMarine(
    val id: Int,
    val name: String,
    val coordinates: Coordinates,
    val creationDate: LocalDate,
    val health: Double,
    val heartCount: Long,
    val loyal: Boolean,
    val meleeWeapon: MeleeWeapon?,
    val chapter: Chapter?
): Serializable {
    companion object {

    }
}
