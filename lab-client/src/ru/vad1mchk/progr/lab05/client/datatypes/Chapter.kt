package ru.vad1mchk.progr.lab05.client.datatypes

import ru.vad1mchk.progr.lab05.client.exceptions.ValidationException
import ru.vad1mchk.progr.lab05.client.messages.Messages

data class Chapter(
    val name: String,
    val parentLegion: String?,
    val marinesCount: Int
) : Validated {
    companion object {
        const val STRICT_MIN_MARINES_COUNT = 0
        const val MAX_MARINES_COUNT = 1000
    }

    override fun validate() {
        if (marinesCount !in (STRICT_MIN_MARINES_COUNT + 1..MAX_MARINES_COUNT)) {
            throw ValidationException("Marines count should be in range (${STRICT_MIN_MARINES_COUNT}; $MAX_MARINES_COUNT]")
        }
    }

    override fun toString(): String {
        return String.format(
            Messages["infoChapterRepresentationFormatString"],
            name,
            parentLegion ?: "-",
            marinesCount
        )
    }
}