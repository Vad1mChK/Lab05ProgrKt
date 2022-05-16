package ru.vad1mchk.progr.lab05.common.datatypes

import ru.vad1mchk.progr.lab05.common.messages.StringResources
import ru.vad1mchk.progr.lab05.common.util.ValueFormatter
import java.io.Serializable
import java.util.*

data class Chapter(
    val name: String,
    val parentLegion: String?,
    val marinesCount: Int
) : Comparable<Chapter>, Validated, Represented, Serializable {
    companion object {
        private val serialVersionUID = 42069668L
        /**
         * Minimum value of [marinesCount]. It should be greater or equal to this.
         */
        const val MIN_MARINES_COUNT = 1

        /**
         * Maximum value of [marinesCount]. It should be less or equal to this.
         */
        const val MAX_MARINES_COUNT = 1000
    }

    override fun validate(): Boolean {
        return marinesCount in MIN_MARINES_COUNT..MAX_MARINES_COUNT
    }

    override fun hashCode(): Int {
        return name.hashCode() xor parentLegion.hashCode() xor marinesCount * (Int.MAX_VALUE shr 10)
    }

    override fun compareTo(other: Chapter): Int {
        return compareValuesBy(
            this,
            other,
            Chapter::name,
            Chapter::parentLegion,
            Chapter::marinesCount
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Chapter) return false
        return this.name == other.name &&
                this.parentLegion == other.parentLegion &&
                this.marinesCount == other.marinesCount
    }

    override fun toString(): String {
        return name.replace("\"", "\"\"").replace(",", "\",\"") + "," + (parentLegion ?: "").replace(
            ",",
            "\",\""
        ) + ",$marinesCount"
    }

    override fun toCoolerString(locale: Locale): String {
        return StringResources().getString("Chapter format").format(
            name,
            parentLegion ?: "-",
            ValueFormatter(locale).formatInt(marinesCount)
        )
    }
}