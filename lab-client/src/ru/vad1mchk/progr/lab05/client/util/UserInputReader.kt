package ru.vad1mchk.progr.lab05.client.util

import ru.vad1mchk.progr.lab05.client.datatypes.Chapter
import ru.vad1mchk.progr.lab05.client.datatypes.Coordinates
import ru.vad1mchk.progr.lab05.client.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.client.exceptions.InvalidFieldValueException
import ru.vad1mchk.progr.lab05.client.exceptions.ValidationException
import ru.vad1mchk.progr.lab05.client.messages.Messages
import java.io.InputStream

object UserInputReader {
    var stream: InputStream = System.`in`
    fun isSystemIn(): Boolean {
        return stream == System.`in`
    }
    fun readMarine(): SpaceMarine {
        TODO()
    }
    fun readCoordinates(): Coordinates {
        var coordinates: Coordinates
        if (isSystemIn()) {
            coordinates = readCoordinatesFromSystemIn()
        } else {
            coordinates = readCoordinatesFromScript()
        }
        return coordinates
    }
    private fun readCoordinatesFromSystemIn(): Coordinates {
        TODO()
    }

    private fun readCoordinatesFromScript(): Coordinates {
        TODO()
    }
    fun readChapter(): Chapter {
        TODO()
    }
}