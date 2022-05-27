package ru.vad1mchk.progr.lab05.common.util

import ru.vad1mchk.progr.lab05.common.exceptions.InvalidDataException

/**
 * Parses a boolean value from string in a better way than the standard
 * [String.toBoolean] function does, and in a more understandable way
 * than the obscure [String.toBooleanStrict].
 */
object BooleanParser {
    /**
     * Parses a boolean value from string (case ignored).
     *
     * @param string String representation of a value.
     * @return `true` if the string is equal to "true", `false` if the
     *     string is equal to "false", ignoring case.
     * @throws InvalidDataException if the string is neither "true" nor
     *     "false".
     */
    @Throws(InvalidDataException::class)
    fun parse(string: String?): Boolean {
        if (string?.lowercase() == "true") return true
        if (string?.lowercase() == "false") return false
        throw InvalidDataException(
            "Это значение должно быть булевым. Введите \"true\" или \"false\" и повторите попытку."
        )
    }
}