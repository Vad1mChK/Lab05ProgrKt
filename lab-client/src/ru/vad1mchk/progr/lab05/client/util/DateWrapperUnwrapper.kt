package ru.vad1mchk.progr.lab05.client.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Class to wrap LocalDate to String for further serialization
 * and unwrap String to LocalDate after deserialization
 */
class DateWrapperUnwrapper private constructor() {
    init {
        throw UnsupportedOperationException("This is an utility class and can not be instantiated")
    }

    companion object {
        /**
         * Wraps the LocalDate into a String representation
         *
         * @param date LocalDate to wrap
         * @return string representation in the form of "dd.MM.yyyy",
         * where dd - day, MM - month, yyyy - year
         */
        fun wrap(date: LocalDate): String {
            return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        }

        /**
         * Unwraps the string representation into a LocalDate object
         *
         * @param str string representation in the form of "dd.MM.yyyy",
         * where dd - day, MM - month, yyyy - year
         * @return unwrapped LocalDate object
         */
        fun unwrap(str: String?): LocalDate {
            return LocalDate.parse(str, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        }
    }
}