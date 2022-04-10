package ru.vad1mchk.progr.lab05.client.datatypes

/**
 * Interface for classes that could have a string representation other than the default [toString] method used in
 * serialization.
 */
interface Represented {
    /**
     * Provides a more user-friendly string representation used in the console output. It looks somewhat like the YAML
     * format.
     * @return A string representation.
     */
    fun toCoolerString(): String
}