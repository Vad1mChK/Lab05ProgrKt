package ru.vad1mchk.progr.lab05.client.io

import ru.vad1mchk.progr.lab05.client.datatypes.Chapter
import ru.vad1mchk.progr.lab05.client.datatypes.Coordinates
import ru.vad1mchk.progr.lab05.client.datatypes.MeleeWeapon
import ru.vad1mchk.progr.lab05.client.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.client.exceptions.InvalidDataException
import ru.vad1mchk.progr.lab05.client.messages.Messages
import java.time.LocalDate
import java.util.*

/**
 * This class manages input from terminal / standard
 * input.
 */
class TerminalInputManager : AbstractInputManager(Scanner(System.`in`)) {
    override fun readName(): String {
        while (true) {
            try {
                OutputManager.say(Messages.inputName)
                return super.readName()
            } catch (e: InvalidDataException) {
                OutputManager.sayException(e)
            }
        }
    }

    override fun readCoordinateX(): Int {
        while (true) {
            try {
                OutputManager.say(Messages.inputCoordinateX, Coordinates.MIN_X)
                return super.readCoordinateX()
            } catch (e: InvalidDataException) {
                OutputManager.sayException(e)
            }
        }
    }

    override fun readCoordinateY(): Float {
        while (true) {
            try {
                OutputManager.say(Messages.inputCoordinateY)
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
                OutputManager.say(Messages.inputHealth, SpaceMarine.MIN_HEALTH)
                return super.readHealth()
            } catch (e: InvalidDataException) {
                OutputManager.sayException(e)
            }
        }
    }

    override fun readHeartCount(): Long {
        while (true) {
            try {
                OutputManager.say(Messages.inputHeartCount, SpaceMarine.MIN_HEART_COUNT, SpaceMarine.MAX_HEART_COUNT)
                return super.readHeartCount()
            } catch (e: InvalidDataException) {
                OutputManager.sayException(e)
            }
        }
    }

    override fun readLoyal(): Boolean {
        while (true) {
            try {
                OutputManager.say(Messages.inputLoyal)
                return super.readLoyal()
            } catch (e: InvalidDataException) {
                OutputManager.sayException(e)
            }
        }
    }

    override fun readMeleeWeapon(): MeleeWeapon? {
        while (true) {
            try {
                OutputManager.say(Messages.inputMeleeWeapon, MeleeWeapon.listConstants())
                OutputManager.sayWarning(Messages.warningNullMeleeWeapon)
                return super.readMeleeWeapon()
            } catch (e: InvalidDataException) {
                OutputManager.sayException(e)
            }
        }
    }

    override fun readChapterName(): String? {
        OutputManager.say(Messages.inputChapterName)
        OutputManager.sayWarning(Messages.warningNullChapter)
        return super.readChapterName()
    }

    override fun readChapterParentLegion(): String? {
        OutputManager.say(Messages.inputChapterParentLegion)
        OutputManager.sayWarning(Messages.warningNullChapterParentLegion)
        return super.readChapterName()
    }

    override fun readChapterMarinesCount(): Int {
        while (true) {
            try {
                OutputManager.say(
                    Messages.inputChapterMarinesCount,
                    Chapter.MIN_MARINES_COUNT,
                    Chapter.MAX_MARINES_COUNT
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
}