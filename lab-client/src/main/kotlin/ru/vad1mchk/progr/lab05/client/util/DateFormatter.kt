package ru.vad1mchk.progr.lab05.client.util

import ru.vad1mchk.progr.lab05.client.exceptions.InvalidDataException
import ru.vad1mchk.progr.lab05.client.messages.Messages
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

/**
 * Object that can format or parse date represented by the pattern in [Messages.formatLocalDate].
 */
object DateFormatter {
    /**
     * Formats date with the [DateTimeFormatter] of pattern in [Messages.formatLocalDate].
     * @param date [LocalDate] that needs to be formatted.
     * @return String representation of [date].
     */
    fun format(date: LocalDate): String {
        return date.format(DateTimeFormatter.ofPattern(Messages.formatLocalDate))
    }

    /**
     * Parses the string representation with the [DateTimeFormatter] according to pattern in [Messages.formatLocalDate].
     * @param formatted The formatted string.
     * @return [LocalDate] parsed from the string.
     * @throws InvalidDataException if date is represented in an invalid format.
     */
    @Throws(InvalidDataException::class)
    fun parse(formatted: String): LocalDate {
        return try {
            LocalDate.parse(formatted, DateTimeFormatter.ofPattern(Messages.formatLocalDate))
        } catch (e: DateTimeParseException) {
            throw InvalidDataException(Messages.exceptionDataInvalidDateFormat, e)
        }
    }
}