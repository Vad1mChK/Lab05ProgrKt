package ru.vad1mchk.progr.lab05.common.datatypes

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeName
import java.io.Serializable

/**
 * Data class that represents a pair of coordinates: x and y.
 * @property x Coordinate of type [Int] that is greater than -817.
 * @property y Coordinate of type [Float].
 */

@JsonTypeName("coordinates")
data class Coordinates(
    @JsonProperty("x") val x: Int,
    @JsonProperty("y") val y: Float
): Serializable {
    companion object {
        const val MIN_X = -817
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
        return "Coordinates (x: $x, y: $y)"
    }
}