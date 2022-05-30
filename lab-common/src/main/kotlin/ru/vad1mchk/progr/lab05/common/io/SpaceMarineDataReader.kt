package ru.vad1mchk.progr.lab05.common.io

import ru.vad1mchk.progr.lab05.common.datatypes.Chapter
import ru.vad1mchk.progr.lab05.common.datatypes.Coordinates
import ru.vad1mchk.progr.lab05.common.datatypes.MeleeWeapon
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.common.exceptions.InvalidDataException
import ru.vad1mchk.progr.lab05.common.util.BooleanParser
import java.time.LocalDate
import java.util.Scanner
import kotlin.system.exitProcess

/**
 * Class to read data about a new instance of [SpaceMarine] interactively or from file.
 */
class SpaceMarineDataReader(val scanner: Scanner = Scanner(System.`in`)) {

    /**
     * Reads the name of the space marine, looping until the name is not blank nor empty.
     * @return The name of the space marine.
     */
    private fun readName(): String {
        while (true) {
            Printer.printNewLine("Введите имя космодесантника:")
            try {
                return scanner.nextLine().also {
                    if (it.isBlank()) throw InvalidDataException("Имя космодесантника не может быть пустым.")
                }
            } catch (e: InvalidDataException) {
                Printer.printError(e)
            }
        }
    }

    /**
     * Reads the X coordinate of the space marine, looping until the coordinate is a valid integer within bounds.
     * @return The X coordinate of the space marine.
     */
    private fun readCoordinatesX(): Int {
        while (true) {
            Printer.printNewLine("Введите координату X (целое число больше ${Coordinates.MIN_X}):")
            try {
                return scanner.nextLine().toInt().also {
                    if (it <= Coordinates.MIN_X) {
                        throw InvalidDataException("Координата X должна быть больше ${Coordinates.MIN_X}")
                    }
                }
            } catch (e: InvalidDataException) {
                Printer.printError(e)
            } catch (e: NumberFormatException) {
                Printer.printError("Введите целое число.")
            }
        }
    }

    /**
     * Reads the Y coordinate of the space marine, looping until the coordinate is a valid float.
     * @return The Y coordinate of the space marine.
     */
    private fun readCoordinatesY(): Float {
        while (true) {
            Printer.printNewLine("Введите координату Y (конечное вещественное число):")
            try {
                return scanner.nextLine().toFloat().also {
                    if (!it.isFinite()) {
                        throw InvalidDataException("Координата Y должна быть конечной.")
                    }
                }
            } catch (e: InvalidDataException) {
                Printer.printError(e)
            } catch (e: NumberFormatException) {
                Printer.printError("Введите вещественное число.")
            }
        }
    }

    /**
     * Reads the coordinates of the space marine.
     * @return The coordinates of the space marine.
     */
    fun readCoordinates(): Coordinates {
        return Coordinates(readCoordinatesX(), readCoordinatesY())
    }

    /**
     * Reads the health of the space marine, looping until the health is a valid positive double.
     * @return The health of the space marine.
     */
    private fun readHealth(): Double {
        while (true) {
            Printer.printNewLine("Введите здоровье (положительное конечное вещественное число):")
            try {
                return scanner.nextLine().toDouble().also {
                    if (!it.isFinite() || it <= SpaceMarine.MIN_HEALTH) {
                        throw InvalidDataException("Здоровье должно быть положительным и конечным.")
                    }
                }
            } catch (e: InvalidDataException) {
                Printer.printError(e)
            } catch (e: NumberFormatException) {
                Printer.printError("Введите вещественное число.")
            }
        }
    }

    /**
     * Reads the heart count of the space marine, looping until the heart count is a valid long within bounds.
     * @return The heart count of the space marine.
     */
    private fun readHeartCount(): Long {
        while (true) {
            Printer.printNewLine("Введите количество сердец (целое число от ${
                SpaceMarine.MIN_HEART_COUNT
            } до ${SpaceMarine.MAX_HEART_COUNT}):")
            try {
                return scanner.nextLine().toLong().also {
                    if (it !in (SpaceMarine.MIN_HEART_COUNT..SpaceMarine.MAX_HEART_COUNT)) {
                        throw InvalidDataException("Количество сердец должно быть в диапазоне от ${
                            SpaceMarine.MIN_HEART_COUNT
                        } до ${SpaceMarine.MAX_HEART_COUNT}.")
                    }
                }
            } catch (e: InvalidDataException) {
                Printer.printError(e)
            } catch (e: NumberFormatException) {
                Printer.printError("Введите целое число.")
            }
        }
    }

    /**
     * Reads if the space marine is loyal, looping until the value is a valid boolean.
     * @return If the space marine is loyal.
     */
    private fun readLoyal(): Boolean {
        while (true) {
            Printer.printNewLine("Введите, лоялен ли десантник (логическое значение из {true, false}):")
            try {
                return BooleanParser.parse(scanner.nextLine())
            } catch (e: InvalidDataException) {
                Printer.printError("Введите логическое значение.")
            }
        }
    }

    /**
     * Reads the melee weapon of the space marine, looping until the value is a valid [MeleeWeapon] enum constant or
     * empty (in the latter case, `null` will be returned).
     * @return The melee weapon of the space marine, if any.
     */
    private fun readMeleeWeapon(): MeleeWeapon? {
        while (true) {
            Printer.printNewLine("Введите тип оружия ближнего боя (из ${
                MeleeWeapon.listAllConstants()
            }, оставьте пустым, если оружия нет):")
            try {
                return MeleeWeapon.valueOf(scanner.nextLine().also {
                    if (it.isBlank()) return null
                })
            } catch (e: IllegalArgumentException) {
                Printer.printError("Введите одно из следующих значений: ${MeleeWeapon.listAllConstants()}.")
            }
        }
    }

    /**
     * Reads the name of the chapter. If it is empty, the whole chapter is null.
     * @return The name of the chapter.
     */
    private fun readChapterName(): String {
        Printer.printNewLine("Введите имя главы (оставьте пустым, если глава не указана):")
        return scanner.nextLine()
    }

    /**
     * Reads the parent legion of the chapter. If it is empty, the parent legion is null.
     * @return The parent legion of the chapter.
     */
    private fun readChapterParentLegion(): String {
        Printer.printNewLine("Введите родительский легион главы (оставьте пустым, если он не указан):")
        return scanner.nextLine()
    }

    /**
     * Reads the marines count of the chapter, looping until the value is a valid integer.
     * @return The marines count of the chapter.
     */
    private fun readChapterMarinesCount(): Int {
        while (true) {
            Printer.printNewLine("Введите количество космодесантников (целое число от ${
                Chapter.MIN_MARINES_COUNT
            } до ${Chapter.MAX_MARINES_COUNT}):")
            try {
                return scanner.nextLine().toInt().also {
                    if (it !in (Chapter.MIN_MARINES_COUNT..Chapter.MAX_MARINES_COUNT)) {
                        throw InvalidDataException("Количество космодесантников должно быть от ${
                            Chapter.MIN_MARINES_COUNT
                        } до ${Chapter.MAX_MARINES_COUNT}.")
                    }
                }
            } catch (e: InvalidDataException) {
                Printer.printError(e.message ?:"")
            } catch (e: NumberFormatException) {
                Printer.printError("Введите целое число.")
            }
        }
    }

    /**
     * Reads the chapter of the space marine, returning `null` if the name is empty and treating parent legion
     * value as `null` if an empty or blank parent legion was entered.
     * @return The chapter.
     */
    private fun readChapter(): Chapter? {
        return Chapter(
            readChapterName().also { it.ifBlank { return null } },
            readChapterParentLegion().also { it.ifBlank { null } },
            readChapterMarinesCount()
        )
    }

    /**
     * Reads the space marine from the user input or file.
     * @return The space marine entered.
     */
    fun readMarine(): SpaceMarine {
        try {
            return SpaceMarine(
                1,
                readName(),
                readCoordinates(),
                LocalDate.now(),
                readHealth(),
                readHeartCount(),
                readLoyal(),
                readMeleeWeapon(),
                readChapter()
            )
        } catch (e: NoSuchElementException) {
            exitProcess(0)
        }
    }
}