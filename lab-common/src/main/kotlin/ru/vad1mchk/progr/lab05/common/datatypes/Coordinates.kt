package ru.vad1mchk.progr.lab05.common.datatypes

import ru.vad1mchk.progr.lab05.common.exceptions.InvalidDataException
import java.io.Serializable

/**
 * Data class that represents a pair of coordinates: x and y.
 *
 * @property x Coordinate of type [Int] that is greater than -817.
 * @property y Coordinate of type [Float].
 */

data class Coordinates(
    val x: Int,
    val y: Float
) : Serializable, Comparable<Coordinates> {
    companion object {
        const val serialVersionUID = 1_073_741_821L
        const val MIN_X = -817
    }

    init {
        if (x <= MIN_X) throw InvalidDataException("Значение x должно быть больше $MIN_X.")
    }

    override fun compareTo(other: Coordinates): Int {
        return compareValuesBy(this, other, Coordinates::x, Coordinates::y)
    }

    override fun hashCode(): Int {
        return x.hashCode() * 31 + y.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Coordinates
        return x == other.x && y == other.y
    }

    override fun toString(): String {
        return "(x: $x, y: $y)"
    }
}