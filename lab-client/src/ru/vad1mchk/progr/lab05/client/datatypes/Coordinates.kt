package ru.vad1mchk.progr.lab05.client.datatypes

import ru.vad1mchk.progr.lab05.client.datatypes.Coordinates.Companion.MIN_X
import ru.vad1mchk.progr.lab05.client.messages.Messages
import java.util.*

/**
 * Data class to store coordinates.
 * @property x x coordinate, should be greater than [MIN_X].
 * @property y y coordinate.
 */
data class Coordinates(val x: Int, val y: Float) : Comparable<Coordinates>, Validated {
    companion object {
        /**
         * Strict minimum value of [x] coordinate. All values less
         * or equal will lead to validation error.
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
        return String.format(Locale.ROOT, "%d,%f", x, y)
    }

    fun toCoolerString(): String {
        return String.format(Locale.ROOT, Messages.formatCoordinates, x, y)
    }

    override fun compareTo(other: Coordinates): Int {
        return compareValuesBy(this, other, Coordinates::x, Coordinates::y)
    }
}
