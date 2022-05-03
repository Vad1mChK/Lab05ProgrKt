package ru.vad1mchk.progr.lab05.common.io

import ru.vad1mchk.progr.lab05.common.datatypes.Chapter
import ru.vad1mchk.progr.lab05.common.datatypes.Coordinates
import ru.vad1mchk.progr.lab05.common.datatypes.MeleeWeapon
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.common.exceptions.InvalidDataException
import ru.vad1mchk.progr.lab05.common.messages.Message
import ru.vad1mchk.progr.lab05.common.util.ValueFormatter
import java.time.LocalDate
import java.util.*

/**
 * This class manages input from terminal / standard input.
 */
class TerminalInputManager(
    locale: Locale,
    val isServer: Boolean = false
) : AbstractInputManager(Scanner(System.`in`), locale) {
    override fun readName(): String {
        while (true) {
            try {
                OutputManager.sayString(stringResources.getString("input name"))
                return super.readName()
            } catch (e: InvalidDataException) {
                OutputManager.sayException(e)
            }
        }
    }

    override fun readCoordinateX(): Int {
        while (true) {
            try {
                OutputManager.sayString(
                    stringResources.getString("input Coordinates x"), formatter.formatInt(Coordinates.MIN_X)
                )
                return super.readCoordinateX()
            } catch (e: InvalidDataException) {
                OutputManager.sayException(e)
            }
        }
    }

    override fun readCoordinateY(): Float {
        while (true) {
            try {
                OutputManager.sayString(stringResources.getString("input Coordinates y"))
                return super.readCoordinateY()
            } catch (e: InvalidDataException) {
                OutputManager.sayException(e)
            }
        }
    }

    override fun readCoordinates(): Coordinates {
        return Coordinates(readCoordinateX(), readCoordinateY())
    }

    override fun readHealth(): Double {
        while (true) {
            try {
                OutputManager.sayString(
                    stringResources.getString("input health"), formatter.formatDouble(SpaceMarine.MIN_HEALTH)
                )
                return super.readHealth()
            } catch (e: InvalidDataException) {
                OutputManager.sayException(e)
            }
        }
    }

    override fun readHeartCount(): Long {
        while (true) {
            try {
                OutputManager.sayString(
                    stringResources.getString("input heartCount"),
                    formatter.formatLong(SpaceMarine.MIN_HEART_COUNT),
                    formatter.formatLong(SpaceMarine.MAX_HEART_COUNT)
                )
                return super.readHeartCount()
            } catch (e: InvalidDataException) {
                OutputManager.sayException(e)
            }
        }
    }

    override fun readLoyal(): Boolean {
        while (true) {
            try {
                OutputManager.sayString(
                    stringResources.getString("input loyal")
                )
                return super.readLoyal()
            } catch (e: InvalidDataException) {
                OutputManager.sayException(e)
            }
        }
    }

    override fun readMeleeWeapon(): MeleeWeapon? {
        while (true) {
            try {
                OutputManager.sayWarning(stringResources.getString("warning nullMeleeWeapon"))
                OutputManager.sayString(stringResources.getString("input meleeWeapon"), MeleeWeapon.listConstants())
                return super.readMeleeWeapon()
            } catch (e: InvalidDataException) {
                OutputManager.sayException(e)
            }
        }
    }

    override fun readChapterName(): String? {
        OutputManager.sayWarning(stringResources.getString("warning nullChapter"))
        OutputManager.sayString(stringResources.getString("input Chapter name"))
        return super.readChapterName()
    }

    override fun readChapterParentLegion(): String? {
        OutputManager.sayWarning(stringResources.getString("warning nullParentLegion"))
        OutputManager.sayString(stringResources.getString("input Chapter parentLegion"))
        return super.readChapterName()
    }

    override fun readChapterMarinesCount(): Int {
        while (true) {
            try {
                OutputManager.sayString(
                    stringResources.getString("input Chapter marinesСount"),
                    formatter.formatInt(Chapter.MIN_MARINES_COUNT),
                    formatter.formatInt(Chapter.MAX_MARINES_COUNT)
                )
                return super.readChapterMarinesCount()
            } catch (e: InvalidDataException) {
                OutputManager.sayException(e)
            }
        }
    }

    override fun readChapter(): Chapter? {
        return readChapterName()?.let {
            Chapter(
                it,
                readChapterParentLegion(),
                readChapterMarinesCount()
            )
        }
    }

    override fun readSpaceMarine(): SpaceMarine {
        return SpaceMarine(
            readName(),
            readCoordinates(),
            LocalDate.now(),
            readHealth(),
            readHeartCount(),
            readLoyal(),
            readMeleeWeapon(),
            readChapter()
        )
    }

    override fun readCommand(): CommandWrapper {
        OutputManager.inviteInput(isServer)
        return super.readCommand()
    }
}