package ru.vad1mchk.progr.lab05.common.datatypes

import ru.vad1mchk.progr.lab05.common.exceptions.InvalidDataException
import java.io.Serializable


/**
 * Data class that represents a chapter of the story.
 *
 * @property name Name of the chapter.
 * @property parentLegion Name of the parent legion (might be null).
 * @property marinesCount Count of the space marines appearing in this
 *     chapter.
 */
data class Chapter(
    val name: String,
    val parentLegion: String?,
    val marinesCount: Int
) : Serializable, Comparable<Chapter> {
    companion object {
        const val serialVersionUID = 1_073_741_822L
        const val MIN_MARINES_COUNT = 1
        const val MAX_MARINES_COUNT = 1000
    }

    init {
        if (marinesCount !in MIN_MARINES_COUNT..MAX_MARINES_COUNT) {
            throw InvalidDataException(
                "Число космодесантников должно быть от $MIN_MARINES_COUNT до $MAX_MARINES_COUNT."
            )
        }
    }

    override fun compareTo(other: Chapter): Int {
        return compareValuesBy(this, other, Chapter::name, Chapter::parentLegion, Chapter::marinesCount)
    }

    override fun hashCode(): Int {
        return ((name.hashCode() * 31) + (parentLegion?.hashCode() ?: 0)) * 31 + marinesCount
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Chapter
        return name == other.name &&
                parentLegion == other.parentLegion &&
                marinesCount == other.marinesCount
    }

    override fun toString(): String {
        return """
            Имя главы: $name
            Родительский легион: ${parentLegion ?: "неизвестен"}
            Кол-во космодесантников: $marinesCount
        """.trimIndent()
    }
}