package ru.vad1mchk.progr.lab05.common.util

import ru.vad1mchk.progr.lab05.common.datatypes.MeleeWeapon
import ru.vad1mchk.progr.lab05.common.exceptions.InvalidDataException
import ru.vad1mchk.progr.lab05.common.messages.StringResources
import java.time.LocalDate
import java.util.*

/**
 * Object used to parse arguments of different types, including varargs, from a string.
 */
object Varargparse {
    val stringResources = StringResources()

    /**
     * Parses the string as an array of elements of type [T].
     * @param string String to parse.
     * @return List of elements of type [T].
     * @throws InvalidDataException if the conversion to type [T] fails.
     */
    @Throws(InvalidDataException::class)
    inline fun <reified T : Any> parseAll(string: String): List<Any?> {
        return splitString(string).map { parse<T>(it) }
    }

    /**
     * Parses the string as an array of elements of type [T].
     * @param string String to parse.
     * @return Element of type [T].
     * @throws InvalidDataException if the conversion to type [T] fails.
     */
    @Throws(InvalidDataException::class)
    inline fun <reified T : Any> parse(string: String, locale: Locale = Locale.ROOT): Any? {
        if (string.isEmpty()) return null
        val parser = ValueParser(locale)
        try {
            return when (T::class) {
                Boolean::class -> parser.parseBoolean(string)
                Int::class -> parser.parseInt(string)
                Long::class -> parser.parseLong(string)
                Float::class -> parser.parseFloat(string)
                Double::class -> parser.parseDouble(string)
                LocalDate::class -> parser.parseLocalDate(string)
                MeleeWeapon::class -> parser.parseMeleeWeapon(string)
                else -> string
            }
        } catch (e: Exception) {
            if (e is NumberFormatException || e is IllegalArgumentException) {
                throw InvalidDataException(
                    String.format(
                        stringResources.getString("InvalidDataException varargparse typeMismatch"),
                        T::class.simpleName
                    ), e
                )
            }
            throw e
        }
    }

    /**
     * Splits a string into a list of strings using [delimiters]. Do not use single quotes `'''`, double quotes `'"'` or
     * backslashes `'\'` as delimiters.
     * @param splittableString String to split.
     * @param delimiters Delimiters that are removed while splitting if not escaped.
     * @return List of strings.
     */
    fun splitString(splittableString: String, vararg delimiters: Char): List<String> {
        val listString = mutableListOf<String>()
        var isSingleQuoteEscaping = false
        var isDoubleQuoteEscaping = false
        var isSlashEscaping = false

        val currentString = StringBuilder()
        for (symbol in splittableString) {
            when (symbol) {
                in delimiters -> {
                    if (!isSingleQuoteEscaping && !isDoubleQuoteEscaping && !isSlashEscaping) {
                        listString.add(currentString.toString())
                        currentString.clear()
                    } else {
                        currentString.append(symbol)
                    }
                }
                '\\' -> {
                    if (!isSlashEscaping) {
                        isSlashEscaping = true
                    } else {
                        isSlashEscaping = false
                        currentString.append(symbol)
                    }
                }
                '"' -> {
                    if (isSlashEscaping) {
                        currentString.append(symbol)
                        isSlashEscaping = false
                    } else {
                        if (!isSingleQuoteEscaping && !isDoubleQuoteEscaping) {
                            isDoubleQuoteEscaping = true
                        } else if (isSingleQuoteEscaping) {
                            currentString.append(symbol)
                        } else {
                            isDoubleQuoteEscaping = false
                        }
                    }
                }
                '\'' -> {
                    if (isSlashEscaping) {
                        currentString.append(symbol)
                        isSlashEscaping = false
                    } else {
                        if (!isSingleQuoteEscaping && !isDoubleQuoteEscaping) {
                            isSingleQuoteEscaping = true
                        } else if (!isSingleQuoteEscaping) {
                            currentString.append(symbol)
                        } else {
                            isSingleQuoteEscaping = false
                        }
                    }
                }
                else -> {
                    currentString.append(symbol)
                    isSlashEscaping = false
                }
            }
        }
        listString.add(currentString.toString())
        return listString.toList()
    }
}