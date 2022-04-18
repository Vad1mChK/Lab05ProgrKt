package ru.vad1mchk.progr.lab05.common.datatypes

/**
 * Interface for datatypes that should undergo validation whilst deserializing the collection file.
 */
interface Validated {
    /**
     * Checks if all the values of the class are correct during deserialization.
     * @return `true` if the values are correct, else `false`
     */
    fun validate(): Boolean
}