package ru.vad1mchk.progr.lab05.client.datatypes

import ru.vad1mchk.progr.lab05.client.messages.Messages

data class Chapter(
    val name: String,
    val parentLegion: String?,
    val marinesCount: Int
) : Comparable<Chapter>, Validated, Represented {
    companion object {
        /**
         * Minimum value of [marinesCount]. It should be greater or
         * equal to this.
         */
        const val MIN_MARINES_COUNT = 1

        /**
         * Maximum value of [marinesCount]. It should be less or
         * equal to this.
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
        return name.replace("\"", "\"\"").replace(",", "\",\"") + "," + (parentLegion ?: "").replace(",", "\",\"") + ",$marinesCount"
    }

    override fun toCoolerString(): String {
        return String.format(Messages.formatChapter, name, parentLegion ?: "-", marinesCount)
    }
}
