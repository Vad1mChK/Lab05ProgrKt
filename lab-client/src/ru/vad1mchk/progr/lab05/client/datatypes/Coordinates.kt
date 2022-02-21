package ru.vad1mchk.progr.lab05.client.datatypes

import ru.vad1mchk.progr.lab05.client.exceptions.IllegalDataValueException
import ru.vad1mchk.progr.lab05.client.util.Wrappable

/**
 * Class to store coordinates
 */
class Coordinates : Wrappable {
    /**
     * x coordinate.
     * Should be >= -817
     */
    var x = 0

    /**
     * y coordinate
     */
    var y = 0f
    override fun wrap(): String {
        return String.format("%d:%f", x, y)
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val that = o as Coordinates
        return x == that.x && java.lang.Float.compare(that.y, y) == 0
    }

    override fun hashCode(): Int {
        return x xor java.lang.Float.hashCode(y) + Int.MAX_VALUE
    }

    override fun toString(): String {
        return "(x: $x, y: $y)"
    }

    companion object {
        /**
         * Unwraps the String representation of coordinates into an object
         *
         * @param str String representation
         * @return Coordinates object
         */
        fun unwrap(str: String): Coordinates {
            val coordinates = Coordinates()
            if (!str.toRegex().matches("^-?[0-9]{1,10}:-?([0-9]+\\.[0-9]*|[0-9]*\\.[0-9]+|[0-9]+)$")) {
                throw IllegalDataValueException("Coordinates are stored in an invalid format")
            }
            val index = str.indexOf(':')
            val xStr = str.substring(0, index)
            val yStr = str.substring(index + 1)
            return try {
                val x = xStr.toInt()
                coordinates.x = x
                val y = yStr.toFloat()
                coordinates.y = y
                coordinates
            } catch (e: NumberFormatException) {
                throw IllegalDataValueException("Coordinates have an invalid number format", e)
            }
        }
    }
}
