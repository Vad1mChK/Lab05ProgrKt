package ru.vad1mchk.progr.lab05.client.styles

import javafx.scene.paint.Color

/**
 * Utility class that manages colors and allows to make colors from integers and object hash codes.
 */
object Colors {
    /**
     * A useful constant for coloring null objects (RGBA: #80C5CDD8).
     */
    @JvmStatic
    val GHOST_COLOR: Color = Color.rgb(197, 205, 216, 0.5)

    /**
     * Obtains a color from an integer number, modulo 16777216, where bytes 23..16 correspond to the red component,
     * bytes 15..8 to the green and 7..0 to the blue.
     * @param intValue Integer value to obtain the color from.
     * @return An RGB color corresponding to this integer value.
     */
    @JvmStatic
    fun colorRgbFromInt(intValue: Int): Color {
        return Color.rgb(
            (intValue and 0xff0000) shr 16,
            (intValue and 0x00ff00) shr 8,
            intValue and 0x0000ff
        )
    }

    /**
     * Obtains a color from an integer number, where bytes 31..24 correspond to the alpha component, bytes 23..16 to the
     * red, 5..8 to the green and 7..0 to the blue.
     * @param intValue Integer value to obtain the color from.
     * @return An RGBA color corresponding to this integer value.
     */
    @JvmStatic
    fun colorRgbaFromInt(intValue: Int): Color {
        return Color.rgb(
            (intValue and 0xff0000) shr 16,
            (intValue and 0x00ff00) shr 8,
            intValue and 0x0000ff,
            ((intValue.toLong() and 0xff000000) shl 24) / 255.0
        )
    }

    /**
     * Obtains an RGB color from this object.
     * @param any Any object to obtain the hash code of.
     * @return An RGB color calculated based on [any]'s hash code. If [any] is null, returns [GHOST_COLOR].
     */
    @JvmStatic
    fun colorRgbFromHashCode(any: Any?): Color {
        if (any == null) {
            return GHOST_COLOR
        }
        return colorRgbFromInt(any.hashCode())
    }

    @JvmStatic
    fun toString(color: Color): String {
        return "#"+color.toString().substring(2).removeSuffix("ff")
    }
}