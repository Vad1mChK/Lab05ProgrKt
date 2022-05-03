package ru.vad1mchk.progr.lab05.common.datatypes

import ru.vad1mchk.progr.lab05.common.datatypes.Coordinates.Companion.MIN_X
import ru.vad1mchk.progr.lab05.common.messages.StringResources
import ru.vad1mchk.progr.lab05.common.util.ValueFormatter
import java.io.Serializable
import java.util.*

/**
 * Data class to store coordinates.
 * @property x x coordinate, should be greater than [MIN_X].
 * @property y y coordinate.
 */
data class Coordinates(val x: Int, val y: Float) : Comparable<Coordinates>, Validated, Serializable {
    companion object {
        /**
         * Strict minimum value of [x] coordinate. All values less or equal will lead to validation failure.
         */
        const val MIN_X = -817
    }

    override fun validate(): Boolean {
        return x > MIN_X && y.isFinite()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other !is Coordinates) {
            return false
        }
        return this.x == other.x && this.y == other.y
    }

    override fun hashCode(): Int {
        return x xor y.hashCode() xor Int.MAX_VALUE
    }

    override fun toString(): String {
        return String.format(Locale.ROOT, "%d,%s", x, y)
    }

    fun toCoolerString(locale: Locale): String {
        val formatter = ValueFormatter(locale)
        return StringResources().getString("Coordinates format").format(
            formatter.formatInt(x),
            formatter.formatFloat(y)
        )
    }

    override fun compareTo(other: Coordinates): Int {
        return compareValuesBy(this, other, Coordinates::x, Coordinates::y)
    }
}