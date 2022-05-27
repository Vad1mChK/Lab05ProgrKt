package ru.vad1mchk.progr.lab05.server.csv

import com.fasterxml.jackson.annotation.JsonProperty
import ru.vad1mchk.progr.lab05.common.datatypes.Chapter
import ru.vad1mchk.progr.lab05.common.datatypes.Coordinates
import ru.vad1mchk.progr.lab05.common.datatypes.MeleeWeapon
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.common.exceptions.InvalidDataException
import java.time.LocalDate

data class FlatSpaceMarine(
    @JsonProperty("id") val id: Int,
    @JsonProperty("name") val name: String,
    @JsonProperty("coordinatesX") val coordinatesX: Int,
    @JsonProperty("coordinatesY") val coordinatesY: Float,
    @JsonProperty("creationDate") val creationDate: LocalDate,
    @JsonProperty("health") val health: Double,
    @JsonProperty("heartCount") val heartCount: Long,
    @JsonProperty("loyal") val loyal: Boolean,
    @JsonProperty("meleeWeapon") val meleeWeapon: MeleeWeapon?,
    @JsonProperty("chapterName") val chapterName: String?,
    @JsonProperty("chapterParentLegion") val chapterParentLegion: String?,
    @JsonProperty("chapterMarinesCount") val chapterMarinesCount: Int?
) {
    companion object {
        /**
         * "Flattens" this instance of [SpaceMarine], turning it into a
         * flat object suitable for CSV.
         *
         * @param source [SpaceMarine] object to flatten.
         * @return The flattened object.
         */
        @JvmStatic
        fun from(source: SpaceMarine): FlatSpaceMarine {
            return FlatSpaceMarine(
                source.id,
                source.name,
                source.coordinates.x,
                source.coordinates.y,
                source.creationDate,
                source.health,
                source.heartCount,
                source.loyal,
                source.meleeWeapon,
                source.chapter?.name,
                source.chapter?.parentLegion,
                source.chapter?.marinesCount
            )
        }
    }

    /**
     * Restore the original [SpaceMarine] from this flattened version.
     * Typically using during CSV deserialization.
     *
     * @return An instance of [SpaceMarine] (non-flattened).
     * @throws InvalidDataException if the object did not pass
     *     validation or a null pointer exception was caught.
     */
    @Throws(InvalidDataException::class)
    fun deflatten(): SpaceMarine {
        val chapter: Chapter? = try {
            if (chapterName == null && chapterParentLegion == null && chapterMarinesCount == null) {
                null
            } else {
                Chapter(chapterName!!, chapterParentLegion, chapterMarinesCount!!)
            }
        } catch (e: NullPointerException) {
            throw InvalidDataException("Ошибка: встречено значение null при десериализации объекта коллекции.", e)
        }
        return SpaceMarine(
            id,
            name,
            Coordinates(coordinatesX, coordinatesY),
            creationDate,
            health,
            heartCount,
            loyal,
            meleeWeapon,
            chapter
        )
    }
}