package ru.vad1mchk.progr.lab05.common.io

import ru.vad1mchk.progr.lab05.common.connection.CommandMessage
import ru.vad1mchk.progr.lab05.common.datatypes.*
import ru.vad1mchk.progr.lab05.common.exceptions.*
import ru.vad1mchk.progr.lab05.common.messages.Messages
import ru.vad1mchk.progr.lab05.common.util.BooleanParser
import java.time.LocalDate
import java.util.*
import kotlin.NoSuchElementException

/**
 * Basic implementation of [InputManager] that  defines common behavior for its descendants.
 */
abstract class InputManagerImpl(var scanner: Scanner) : InputManager {
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

    override fun readCommand(): CommandMessage {
        val spaceMarine: SpaceMarine?
        try {
            scanner.nextLine().trim().split(' ').also {
                if (it.isEmpty()) throw EmptyCommandException()
                spaceMarine = if (it[0] in listOf("add", "update", "add_if_less", "remove_if_greater")) {
                    readSpaceMarine()
                } else null
                return CommandMessage(it[0], it.slice(1 until it.size).toTypedArray().ifEmpty { null }, spaceMarine)
            }
        } catch (e: NoSuchElementException) {
            throw EndProgramException()
        }
    }

    override fun scanner(): Scanner {
        return scanner
    }
}