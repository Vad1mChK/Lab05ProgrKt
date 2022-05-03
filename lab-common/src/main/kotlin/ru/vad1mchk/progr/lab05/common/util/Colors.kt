package ru.vad1mchk.progr.lab05.common.util

import javafx.scene.paint.Color
import ru.vad1mchk.progr.lab05.common.util.Colors.hashCode

/**
 * Object to convert int values to RGB colors. It is used to paint the displayed objects different colors according
 * to their owner's name's [hashCode].
 */
object Colors { // Added as a preparation for future updates

    /**
     * Converts the integer value to a JavaFX color.
     * @param intColor The integer value. Only the lowest 3 bytes are significant.
     * @return The JavaFX color corresponding to the RGB color triple represented by [intColor].
     */
    fun asJavaFxColor(intColor: Int): Color {
        val colorTriple = toRGBTriple(intColor)
        return Color.rgb(colorTriple.first, colorTriple.second, colorTriple.third)
    }

    /**
     * Converts the integer value to a triple of RGB values. The 3rd byte is treated as red, the 2nd as green, and
     * the 1st as blue. All higher bytes are ignored.
     * @param intColor The integer value. Only the lowest 3 bytes are significant.
     * @return A triple of integer values: red, green, and blue.
     */
    private fun toRGBTriple(intColor: Int): Triple<Int, Int, Int> {
        return Triple(
            (intColor and 0xff0000) shr 16,
            (intColor and 0x00ff00) shr 8,
            intColor and 0x0000ff
        )
    }
}