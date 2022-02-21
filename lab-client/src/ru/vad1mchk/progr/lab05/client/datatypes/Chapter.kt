package ru.vad1mchk.progr.lab05.client.datatypes

/**
 * Class that represents chapters.
 * Each character has its name, parent legion, and space marines
 */
data class Chapter(val name: String, val parentLegion: String?, val marinesCount: Int)
