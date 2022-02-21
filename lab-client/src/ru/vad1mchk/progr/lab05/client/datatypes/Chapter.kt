package ru.vad1mchk.progr.lab05.client.datatypes

/**
 * Class that represents chapters.
 * Each character has its name, parent legion, and space marines
 */
class Chapter {
    /**
     * Name of a chapter.
     * Should not be null nor empty
     */
    private val name: String? = null

    /**
     * Parent legion
     */
    private val parentLegion: String? = null

    /**
     * Should be 0 < marinesCount <= 1000
     */
    private val marinesCount = 0
}