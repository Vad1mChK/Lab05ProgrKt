package ru.vad1mchk.progr.lab05.common.io

import ru.vad1mchk.progr.lab05.common.datatypes.Chapter
import ru.vad1mchk.progr.lab05.common.datatypes.Coordinates
import ru.vad1mchk.progr.lab05.common.datatypes.MeleeWeapon
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.common.exceptions.EmptyCommandException
import ru.vad1mchk.progr.lab05.common.exceptions.EndProgramException
import ru.vad1mchk.progr.lab05.common.exceptions.InvalidDataException
import ru.vad1mchk.progr.lab05.common.messages.StringResources
import ru.vad1mchk.progr.lab05.common.util.BooleanParser
import ru.vad1mchk.progr.lab05.common.util.ValueFormatter
import ru.vad1mchk.progr.lab05.common.util.ValueParser
import ru.vad1mchk.progr.lab05.common.util.Varargparse
import java.time.LocalDate
import java.util.*
import kotlin.NoSuchElementException

/**
 * Basic implementation of an input manager that defines common behavior for its descendants.
 * @property scanner The scanner used by this input manager.
 * @property locale Locale to use to parse input and format feedback.
 */
abstract class AbstractInputManager(var scanner: Scanner, var locale: Locale) : InputManager {
    companion object {
        val stringResources = StringResources()
    }
    val formatter = ValueFormatter(locale)
    val parser = ValueParser(locale)

    init {
        scanner.useDelimiter("\n")
    }

    override fun readName(): String {
        scanner.nextLine().trim().also {
            if (it.isEmpty()) {
                throw InvalidDataException(stringResources.getString("InvalidDataException notNull"))
            }
            return it
        }
    }

    override fun readCoordinateX(): Int {

        scanner.nextLine().trim().also {
            try {
                if (parser.parseInt(it) <= Coordinates.MIN_X) {
                    throw InvalidDataException(
                        stringResources.getString("InvalidDataException numberShouldBeGreaterThan")
                            .format(formatter.formatInt(Coordinates.MIN_X))
                    )
                }
                return parser.parseInt(it)
            } catch (e: NumberFormatException) {
                throw InvalidDataException(stringResources.getString("InvalidDataException numberFormat"), e)
            }
        }
    }

    override fun readCoordinateY(): Float {
        scanner.nextLine().trim().also {
            try {
                if (!parser.parseFloat(it).isFinite()) {
                    throw InvalidDataException(
                        stringResources.getString("InvalidDataException numberShouldBeFinite")
                    )
                }
                return parser.parseFloat(it)
            } catch (e: NumberFormatException) {
                throw InvalidDataException(stringResources.getString("InvalidDataException numberFormat"), e)
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
                        stringResources.getString("InvalidDataException numberShouldBeGreaterThan")
                            .format(formatter.formatDouble(SpaceMarine.MIN_HEALTH))
                    )
                }
                if (!it.toDouble().isFinite()) {
                    throw InvalidDataException(
                        stringResources.getString("InvalidDataException numberShouldBeFinite")
                    )
                }
                return it.toDouble()
            } catch (e: NumberFormatException) {
                throw InvalidDataException(stringResources.getString("InvalidDataException numberFormat"), e)
            }
        }
    }

    override fun readHeartCount(): Long {
        scanner.nextLine().trim().also {
            try {
                if (parser.parseLong(it) !in
                    (SpaceMarine.MIN_HEART_COUNT..SpaceMarine.MAX_HEART_COUNT)
                ) {
                    throw InvalidDataException(
                        stringResources.getString("InvalidDataException numberShouldBeInRange").format(
                            formatter.formatLong(SpaceMarine.MIN_HEART_COUNT),
                            formatter.formatLong(SpaceMarine.MAX_HEART_COUNT)
                        )
                    )
                }
                return it.toLong()
            } catch (e: NumberFormatException) {
                throw InvalidDataException(stringResources.getString("InvalidDataException numberFormat"), e)
            }
        }
    }

    override fun readLoyal(): Boolean {
        return parser.parseBoolean(scanner.nextLine().trim())
    }

    override fun readMeleeWeapon(): MeleeWeapon? {
        scanner.nextLine().trim().also {
            if (it.isEmpty()) return null
            try {
                return parser.parseMeleeWeapon(it)
            } catch (e: IllegalArgumentException) {
                throw InvalidDataException(
                    stringResources.getString("InvalidDataException enumConstant")
                        .format(MeleeWeapon.listConstants()),
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
                        stringResources.getString("InvalidDataException numberShouldBeInRange").format(
                            formatter.formatInt(Chapter.MIN_MARINES_COUNT),
                            formatter.formatInt(Chapter.MAX_MARINES_COUNT)
                        )
                    )
                }
                return it.toInt()
            } catch (e: NumberFormatException) {
                throw InvalidDataException(stringResources.getString("InvalidDataException numberFormat"), e)
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
        return try {
            scanner.nextLine().let { line ->
                val list = Varargparse.splitString(line, ' ')
                if (list.isEmpty()) throw EmptyCommandException()
                CommandWrapper(list[0], list.slice(1 until list.size).toTypedArray())
            }
        } catch (e: NoSuchElementException) {
            throw EndProgramException()
        }
    }

    override fun scanner(): Scanner {
        return scanner
    }
}