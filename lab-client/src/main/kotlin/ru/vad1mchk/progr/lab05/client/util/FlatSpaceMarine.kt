package ru.vad1mchk.progr.lab05.client.util

import ru.vad1mchk.progr.lab05.common.datatypes.Chapter
import ru.vad1mchk.progr.lab05.common.datatypes.Coordinates
import ru.vad1mchk.progr.lab05.common.datatypes.MeleeWeapon
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import java.time.LocalDate

data class FlatSpaceMarine(
    val id: Int,
    val creatorName: String?,
    val name: String,
    val coordinatesX: Int,
    val coordinatesY: Float,
    val creationDate: LocalDate,
    val health: Double,
    val heartCount: Long,
    val loyal: Boolean,
    val meleeWeapon: MeleeWeapon?,
    val chapterName: String?,
    val chapterParentLegion: String?,
    val chapterMarinesCount: Int?
) {
    companion object {
        @JvmStatic
        fun fromSpaceMarine(spaceMarine: SpaceMarine): FlatSpaceMarine {
            val (id, creatorName, name, coordinates, creationDate, health, heartCount, loyal, meleeWeapon, chapter) =
                spaceMarine
            return FlatSpaceMarine(
                id,
                creatorName,
                name,
                coordinates.x,
                coordinates.y,
                creationDate,
                health,
                heartCount,
                loyal,
                meleeWeapon,
                chapter?.name,
                chapter?.parentLegion,
                chapter?.marinesCount
            )
        }
    }

    fun toSpaceMarine(): SpaceMarine {
        return SpaceMarine(
            id,
            creatorName,
            name,
            Coordinates(coordinatesX, coordinatesY),
            creationDate,
            health,
            heartCount,
            loyal,
            meleeWeapon,
            chapterName?.let { Chapter(it, chapterParentLegion, chapterMarinesCount ?: 1) }
        )
    }
}
