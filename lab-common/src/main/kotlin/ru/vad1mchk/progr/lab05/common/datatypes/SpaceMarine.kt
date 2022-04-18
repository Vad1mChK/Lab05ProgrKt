package ru.vad1mchk.progr.lab05.common.datatypes

import ru.vad1mchk.progr.lab05.common.messages.Messages
import ru.vad1mchk.progr.lab05.common.util.DateFormatter
import java.time.LocalDate
import java.util.*
import java.io.Serializable

/**
 * Class to store information about a space marine. Enough said.
 *
 * @property name A name, should not be empty.
 * @property coordinates [Coordinates] where the marine is located.
 * @property creationDate Creation date, generated automatically.
 * @property health Health, should be more than 0.
 * @property heartCount Heart count, should be in range (1..3).
 * @property loyal If the marine is loyal.
 * @property meleeWeapon The [MeleeWeapon] the marine wields, can be null.
 * @property chapter The chapter the marine appears in, can be null.
 */
data class SpaceMarine(
    val name: String,
    val coordinates: Coordinates,
    val creationDate: LocalDate,
    val health: Double,
    val heartCount: Long,
    val loyal: Boolean,
    val meleeWeapon: MeleeWeapon?,
    val chapter: Chapter?
) : Comparable<SpaceMarine>, Represented, Validated, Serializable {
    /**
     * Unique identifier of [SpaceMarine], generated automatically.
     */
    var id: Int = 1

    companion object {
        /**
         * Minimum value for [id]. It should be greater or equal to this.
         */
        const val MIN_ID = 1

        /**
         * Strict minimum value of [health]. If it's less or equal to this, it will lead to validation failure.
         */
        const val MIN_HEALTH = 0.0

        /**
         * Minimum value for [heartCount]. It should be greater or equal to this.
         */
        const val MIN_HEART_COUNT = 1

        /**
         * Maximum value for [heartCount]. It should be less or equal to this.
         */
        const val MAX_HEART_COUNT = 3
    }

    override fun compareTo(other: SpaceMarine): Int {
        return compareValuesBy(
            this,
            other,
            SpaceMarine::name,
            SpaceMarine::id,
            SpaceMarine::coordinates,
            SpaceMarine::creationDate,
            SpaceMarine::health,
            SpaceMarine::heartCount,
            SpaceMarine::loyal,
            SpaceMarine::meleeWeapon,
            SpaceMarine::chapter
        )
    }

    override fun validate(): Boolean {
        return id >= MIN_ID &&
                health > MIN_HEALTH &&
                heartCount in (MIN_HEART_COUNT..MAX_HEART_COUNT) &&
                coordinates.validate() &&
                chapter?.validate() ?: true
    }

    override fun hashCode(): Int {
        return id xor
                name.hashCode() xor
                coordinates.hashCode() xor
                creationDate.hashCode() xor
                health.hashCode() xor
                heartCount.hashCode() * Int.MAX_VALUE / 4 xor
                loyal.hashCode() * Int.MAX_VALUE xor
                meleeWeapon.hashCode() xor
                chapter.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SpaceMarine) return false
        return this.id == other.id &&
                this.name == other.name &&
                this.coordinates == other.coordinates &&
                this.creationDate == other.creationDate &&
                this.health == other.health &&
                this.heartCount == other.heartCount &&
                this.loyal == other.loyal &&
                this.meleeWeapon == other.meleeWeapon &&
                this.chapter == other.chapter
    }

    override fun toString(): String {
        return listOf(
            id,
            name.replace("\"", "\"\"").replace(",", "\",\""),
            coordinates,
            DateFormatter.format(creationDate),
            health,
            heartCount,
            loyal,
            meleeWeapon,
            chapter ?: ",,"
        ).joinToString(",") { (it ?: "").toString() }
    }

    override fun toCoolerString(): String {
        return String.format(
            Locale.ROOT,
            Messages.formatSpaceMarine,
            name,
            id,
            coordinates.toCoolerString(),
            DateFormatter.format(creationDate),
            health,
            heartCount,
            loyal,
            meleeWeapon ?: "-",
            chapter?.toCoolerString() ?: "-"
        )
    }
}