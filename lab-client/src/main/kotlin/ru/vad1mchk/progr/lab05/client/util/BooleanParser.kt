package ru.vad1mchk.progr.lab05.client.util

import ru.vad1mchk.progr.lab05.client.exceptions.InvalidDataException
import ru.vad1mchk.progr.lab05.client.messages.Messages

/**
 * Object that allows to parse booleans in a more
 * accurate way, throwing an exception when it's
 * needed.
 */
object BooleanParser {
    /**
     * Parses a boolean value from string (case ignored).
     * @param string String representation of a value.
     * @return `true` if the string is equal to "true",
     * `false` if the string is equal to "false",
     * ignoring case.
     * @throws InvalidDataException if the string is
     * neither "true" nor "false".
     */
    @Throws(InvalidDataException::class)
    fun parse(string: String?): Boolean {
        if (string?.lowercase() == "true") return true
        if (string?.lowercase() == "false") return false
        throw InvalidDataException(Messages.exceptionDataInvalidBoolean)
    }
}