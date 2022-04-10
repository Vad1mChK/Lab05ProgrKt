package ru.vad1mchk.progr.lab05.client.io

import ru.vad1mchk.progr.lab05.client.command.CommandWrapper
import ru.vad1mchk.progr.lab05.client.datatypes.Chapter
import ru.vad1mchk.progr.lab05.client.datatypes.Coordinates
import ru.vad1mchk.progr.lab05.client.datatypes.MeleeWeapon
import ru.vad1mchk.progr.lab05.client.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.client.exceptions.EndProgramException
import ru.vad1mchk.progr.lab05.client.exceptions.InvalidDataException
import ru.vad1mchk.progr.lab05.client.messages.Messages
import ru.vad1mchk.progr.lab05.client.util.BooleanParser
import java.time.LocalDate
import java.util.*

/**
 * Basic implementation of [InputManager] that  defines common behavior for its descendants.
 */
abstract class AbstractInputManager(var scanner: Scanner) : InputManager {
    init {
        scanner.useDelimiter("\n")
    }

    override fun readName(): String {
        scanner.nextLine().trim().also {
            if (it.isEmpty()) {
                throw InvalidDataException(Messages.exceptionDataInvalidNotNull)
            }
            return it
        }
    }

    override fun readCoordinateX(): Int {
        scanner.nextLine().trim().also {
            try {
                if (it.toInt() <= Coordinates.MIN_X) {
                    throw InvalidDataException(
                        String.format(
                            Messages.exceptionDataInvalidNumberShouldBeGreaterThanInt,
                            Coordinates.MIN_X
                        )
                    )
                }
                return it.toInt()
            } catch (e: NumberFormatException) {
                throw InvalidDataException(Messages.exceptionDataInvalidNumberFormat, e)
            }
        }
    }

    override fun readCoordinateY(): Float {
        scanner.nextLine().trim().also {
            try {
                if (!it.toFloat().isFinite()) {
                    throw InvalidDataException(Messages.exceptionDataInvalidNumberShouldBeFinite)
                }
                return it.toFloat()
            } catch (e: NumberFormatException) {
                throw InvalidDataException(Messages.exceptionDataInvalidNumberFormat, e)
            }
        }
    }

    override fun readCoordinates(): Coordinates {
        return Coordinates(readCoordinateX(), readCoordinateY())
    }

    override fun readHealth(): Double {
        scanner.nextLine().trim().also {
            try {
                if (it.toDouble() <= SpaceMarine.MIN_HEALTH) {
                    throw InvalidDataException(
                        String.format(
                            Locale.ROOT,
                            Messages.exceptionDataInvalidNumberShouldBeGreaterThanFloat,
                            SpaceMarine.MIN_HEALTH
                        )
                    )
                }
                if (!it.toDouble().isFinite()) {
                    throw InvalidDataException(Messages.exceptionDataInvalidNumberShouldBeFinite)
                }
                return it.toDouble()
            } catch (e: NumberFormatException) {
                throw InvalidDataException(Messages.exceptionDataInvalidNumberFormat, e)
            }
        }
    }

    override fun readHeartCount(): Long {
        scanner.nextLine().trim().also {
            try {
                if (it.toLong() !in
                    (SpaceMarine.MIN_HEART_COUNT..SpaceMarine.MAX_HEART_COUNT)
                ) {
                    throw InvalidDataException(
                        String.format(
                            Messages.exceptionDataInvalidNumberShouldBeInRangeIntOrLong,
                            SpaceMarine.MIN_HEART_COUNT,
                            SpaceMarine.MAX_HEART_COUNT
                        )
                    )
                }
                return it.toLong()
            } catch (e: NumberFormatException) {
                throw InvalidDataException(Messages.exceptionDataInvalidNumberFormat, e)
            }
        }
    }

    override fun readLoyal(): Boolean {
        return BooleanParser.parse(scanner.nextLine().trim())
    }

    override fun readMeleeWeapon(): MeleeWeapon? {
        scanner.nextLine().trim().also {
            if (it.isEmpty()) return null
            try {
                return MeleeWeapon.valueOf(it)
            } catch (e: IllegalArgumentException) {
                throw InvalidDataException(
                    String.format(
                        Messages.exceptionDataInvalidEnumConstant,
                        MeleeWeapon.listConstants()
                    ),
                    e
                )
            }
        }
    }

    override fun readChapterName(): String? {
        scanner.nextLine().trim().also {
            return it.ifEmpty { null }
        }
    }

    override fun readChapterParentLegion(): String? {
        scanner.nextLine().trim().also {
            return it.ifEmpty { null }
        }
    }

    override fun readChapterMarinesCount(): Int {
        scanner.nextLine().trim().also {
            try {
                if (it.toInt() !in
                    (Chapter.MIN_MARINES_COUNT..Chapter.MAX_MARINES_COUNT)
                ) {
                    throw InvalidDataException(
                        String.format(
                            Messages.exceptionDataInvalidNumberShouldBeInRangeIntOrLong,
                            Chapter.MIN_MARINES_COUNT,
                            Chapter.MAX_MARINES_COUNT
                        )
                    )
                }
                return it.toInt()
            } catch (e: NumberFormatException) {
                throw InvalidDataException(Messages.exceptionDataInvalidNumberFormat, e)
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
        try {
            scanner.nextLine().trim().also { commandString ->
                return if (' ' in commandString) {
                    CommandWrapper(
                        commandString.split(" ", limit = 2)[0],
                        commandString.split(" ", limit = 2)[1]
                    )
                } else {
                    CommandWrapper(commandString)
                }
            }
        } catch (e: NoSuchElementException) {
            throw EndProgramException(Messages.exceptionEndProgram)
        }
    }

    override fun scanner(): Scanner {
        return scanner
    }
}