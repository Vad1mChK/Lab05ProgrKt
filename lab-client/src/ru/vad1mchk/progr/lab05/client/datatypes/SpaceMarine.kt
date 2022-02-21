package ru.vad1mchk.progr.lab05.client.datatypes

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * A space marine (O Shikarniy Sup Navarili O Velikiy Sup Navarili)
 */
class SpaceMarine : Comparable<SpaceMarine> {
    /**
     * Unique ID of the space marine.
     * Should be >= 0.
     * Should be unique.
     * Is generated automatically
     */
    var id = 0

    /**
     * Name of the space marine.
     * Should not be null
     */
    var name: String? = null

    /**
     * Coordinates of the space marine.
     * Should not be null
     */
    var coordinates: Coordinates? = null

    /**
     * Date of creation of the space marine.
     * Should not be null.
     * Is generated automatically
     */
    private val creationDate: LocalDate

    /**
     * Health points of the space marine.
     * Should be > 0.
     * Should not be null
     */
    var health: Double? = null

    /**
     * Heart count of the space marine.
     * Should be 0 < heartCount <= 3
     */
    var heartCount: Long = 0

    /**
     * If the space marine is loyal
     */
    var isLoyal = false

    /**
     * A weapon currently equipped by the space marine
     */
    var meleeWeapon: MeleeWeapon? = null

    /**
     * A chapter in which the space marine appears
     */
    var chapter: Chapter? = null

    /**
     * Primary empty constructor for the SpaceMarine class
     */
    init {
        creationDate = LocalDate.now()
    }

    override fun compareTo(other: SpaceMarine): Int {
        return java.lang.String.CASE_INSENSITIVE_ORDER.compare(name, other.name)
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val that = o as SpaceMarine
        return (id == that.id && heartCount == that.heartCount && isLoyal == that.isLoyal && name == that.name && coordinates!!.equals(
            that.coordinates
        )
                && creationDate == that.creationDate && health == that.health && meleeWeapon == that.meleeWeapon
                && chapter == that.chapter)
    }

    override fun hashCode(): Int {
        return Objects.hash(id, name, coordinates, creationDate, health, heartCount, isLoyal, meleeWeapon, chapter)
    }

    override fun toString(): String {
        return ("SpaceMarine {"
                + "id=" + id
                + ", name='" + name + '\''
                + ", coordinates=" + coordinates
                + ", creationDate=" + creationDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                + ", health=" + health
                + ", heartCount=" + heartCount
                + ", loyal=" + isLoyal
                + ", meleeWeapon=" + meleeWeapon
                + ", chapter=" + chapter + '}')
    }
}