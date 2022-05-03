package ru.vad1mchk.progr.lab05.common.util

import ru.vad1mchk.progr.lab05.common.datatypes.MeleeWeapon
import ru.vad1mchk.progr.lab05.common.exceptions.InvalidDataException
import ru.vad1mchk.progr.lab05.common.messages.StringResources
import java.text.DateFormat
import java.text.NumberFormat
import java.text.ParseException
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

/**
 * Parses the values according to the format corresponding to the specified [locale].
 * @property locale Locale to use.
 */
class ValueParser(private val locale: Locale = Locale.ROOT) {
    companion object {
        val stringResources = StringResources()
    }

    /**
     * Parses a boolean value from string (case ignored).
     * @param string String representation of a value.
     * @return `true` if the string is equal to "true", `false` if the string is equal to "false", ignoring case.
     * @throws InvalidDataException if the string is neither "true" nor "false".
     */
    @Throws(InvalidDataException::class)
    fun parseBoolean(string: String?): Boolean {
        if (string?.lowercase(locale) == "true") return true
        if (string?.lowercase(locale) == "false") return false
        throw InvalidDataException(Varargparse.stringResources.getString("InvalidDataException boolean"))
    }

    /**
     * Parses an integer value from string.
     * @param string String representation of a value.
     * @return [Int] value represented by this string.
     * @throws InvalidDataException if the string cannot be converted to any number.
     */
    @Throws(InvalidDataException::class)
    fun parseInt(string: String): Int {
        val formatter = NumberFormat.getInstance(locale)
        formatter.isParseIntegerOnly = true
        return try {
            formatter.parse(string).toInt()
        } catch (e: ParseException) {
            throw InvalidDataException(stringResources.getString("InvalidDataException numberFormat"))
        }
    }

    /**
     * Parses a long value from string.
     * @param string String representation of a value.
     * @return [Long] value represented by this string.
     * @throws InvalidDataException if the string cannot be converted to any number.
     */
    @Throws(InvalidDataException::class)
    fun parseLong(string: String): Long {
        val formatter = NumberFormat.getInstance(locale)
        formatter.isParseIntegerOnly = true
        return try {
            formatter.parse(string).toLong()
        } catch (e: ParseException) {
            throw InvalidDataException(stringResources.getString("InvalidDataException numberFormat"))
        }
    }

    /**
     * Parses a float value from string.
     * @param string String representation of a value.
     * @return [Float] value represented by this string.
     * @throws InvalidDataException if the string cannot be converted to any number.
     */
    @Throws(InvalidDataException::class)
    fun parseFloat(string: String): Float {
        val formatter = NumberFormat.getInstance(locale)
        return try {
            formatter.parse(string).toFloat()
        } catch (e: ParseException) {
            throw InvalidDataException(stringResources.getString("InvalidDataException numberFormat"))
        }
    }

    /**
     * Parses a double value from string.
     * @param string String representation of a value.
     * @return [Double] value represented by this string.
     * @throws InvalidDataException if the string cannot be converted to any number.
     */
    @Throws(InvalidDataException::class)
    fun parseDouble(string: String): Double {
        val formatter = NumberFormat.getInstance(locale)
        return try {
            formatter.parse(string).toDouble()
        } catch (e: ParseException) {
            throw InvalidDataException(stringResources.getString("InvalidDataException numberFormat"))
        }
    }

    /**
     * Parses the string representation with the [DateFormat] according to current locale.
     * @param string The formatted string.
     * @return [LocalDate] parsed from the string.
     * @throws InvalidDataException if date is represented in an invalid format.
     */
    @Throws(InvalidDataException::class)
    fun parseLocalDate(string: String): LocalDate {
        val formatter = DateFormat.getDateInstance(DateFormat.FULL, locale)
        formatter.timeZone = TimeZone.getDefault()
        return try {
            formatter.parse(string).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        } catch (e: ParseException) {
            throw InvalidDataException(stringResources.getString("InvalidDataException dateFormat"))
        }
    }

    /**
     * Parses the string representation of the melee weapon.
     * @param string String representation to parse.
     * @return Melee weapon represented by this string.
     * @throws IllegalArgumentException if the constant could not be parsed.
     */
    @Throws(IllegalArgumentException::class)
    fun parseMeleeWeapon(string: String): MeleeWeapon? {
        if (string.isEmpty()) return null
        return MeleeWeapon.valueOf(string)
    }
}