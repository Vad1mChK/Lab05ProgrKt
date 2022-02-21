package ru.vad1mchk.progr.lab05.client.util

/**
 * Interface implemented by all classes that can be wrapped to String
 * to further be stored in CSV file or printed if needed
 */
interface Wrappable {
    /**
     * Wraps an object into a String
     *
     * @return string representation
     */
    fun wrap(): String?
}