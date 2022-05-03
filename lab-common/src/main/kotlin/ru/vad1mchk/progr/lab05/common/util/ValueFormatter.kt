package ru.vad1mchk.progr.lab05.common.util

import ru.vad1mchk.progr.lab05.common.datatypes.MeleeWeapon
import java.text.DateFormat
import java.text.NumberFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

/**
 * Formats the values according to the format corresponding to the specified [locale].
 * @property locale Locale to use.
 */
class ValueFormatter(private val locale: Locale) {
    companion object {
        private val DEFAULT_MIN_FRACTION_DIGITS = 0
        private val DEFAULT_MAX_FRACTION_DIGITS = 8
    }

    /**
     * Formats this value according to the locale in use.
     * @param booleanValue [Boolean] value to format.
     * @return The string representation of the value.
     */
    fun formatBoolean(booleanValue: Boolean): String {
        return booleanValue.toString()
    }

    /**
     * Formats this value according to the locale in use.
     * @param intValue [Int] value to format.
     * @return The string representation of the value.
     */
    fun formatInt(intValue: Int): String {
        val formatter = NumberFormat.getInstance(locale)
        return formatter.format(intValue)
    }

    /**
     * Formats this value according to the locale in use.
     * @param longValue [Long] value to format.
     * @return The string representation of the value.
     */
    fun formatLong(longValue: Long): String {
        val formatter = NumberFormat.getInstance(locale)
        return formatter.format(longValue)
    }

    /**
     * Formats this value according to the locale in use.
     * @param floatValue [Float] value to format.
     * @return The string representation of the value.
     */
    fun formatFloat(floatValue: Float): String {
        val formatter = NumberFormat.getInstance(locale)
        formatter.minimumFractionDigits = DEFAULT_MIN_FRACTION_DIGITS
        formatter.maximumFractionDigits = DEFAULT_MAX_FRACTION_DIGITS
        return formatter.format(floatValue)
    }

    /**
     * Formats this value according to the locale in use.
     * @param doubleValue [Double] value to format.
     * @return The string representation of the value.
     */
    fun formatDouble(doubleValue: Double): String {
        val formatter = NumberFormat.getInstance(locale)
        formatter.minimumFractionDigits = DEFAULT_MIN_FRACTION_DIGITS
        formatter.maximumFractionDigits = DEFAULT_MAX_FRACTION_DIGITS
        return formatter.format(doubleValue)
    }

    /**
     * Formats this date according to the locale in use.
     * @param dateValue [LocalDate] value to format.
     * @returnThe string representation of the date.
     */
    fun formatLocalDate(dateValue: LocalDate): String {
        val formatter = DateFormat.getDateInstance(DateFormat.FULL, locale)
        return formatter.format(Date.from(dateValue.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
    }

    /**
     * Formats this melee weapon according to the locale in use.
     * @param meleeWeaponValue [MeleeWeapon] value to format.
     * @returnThe string representation of the melee weapon.
     */
    fun formatMeleeWeapon(meleeWeaponValue: MeleeWeapon?): String {
        return meleeWeaponValue?.name?.lowercase()?.replace('_', ' ') ?: "-"
    }
}