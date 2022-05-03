package ru.vad1mchk.progr.lab05.common.datatypes

import java.util.*

/**
 * Interface that provides a more user-friendly string representation of an object. It looks somewhat like the YAML
 * format.
 */
interface Represented {
    /**
     * Provides a user-friendly string representation used in the console output according to the current locale.
     * @param locale Locale to use.
     * @return A string representation.
     */
    fun toCoolerString(locale: Locale): String
}