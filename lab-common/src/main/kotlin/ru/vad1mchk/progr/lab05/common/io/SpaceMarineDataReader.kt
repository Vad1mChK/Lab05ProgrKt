package ru.vad1mchk.progr.lab05.common.io

import ru.vad1mchk.progr.lab05.common.datatypes.Chapter
import ru.vad1mchk.progr.lab05.common.datatypes.Coordinates
import ru.vad1mchk.progr.lab05.common.datatypes.MeleeWeapon
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.common.exceptions.InvalidDataException
import ru.vad1mchk.progr.lab05.common.util.BooleanParser
import java.nio.charset.Charset
import java.time.LocalDate
import java.util.Scanner
import kotlin.system.exitProcess

/**
 * Class to read data about a new instance of [SpaceMarine] interactively.
 */
class SpaceMarineDataReader(val scanner: Scanner = Scanner(System.`in`)) {
    private var currentSpaceMarine: SpaceMarine? = null

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

    fun readCoordinates(): Coordinates {
        return Coordinates(readCoordinatesX(), readCoordinatesY())
    }

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

    private fun readChapterName(): String {
        Printer.printNewLine("Введите имя главы (оставьте пустым, если глава не указана):")
        return scanner.nextLine()
    }

    private fun readChapterParentLegion(): String {
        Printer.printNewLine("Введите родительский легион главы (оставьте пустым, если он не указан):")
        return scanner.nextLine()
    }

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

    private fun readChapter(): Chapter? {
        return Chapter(
            readChapterName().also { it.ifBlank { return null } },
            readChapterParentLegion().also { it.ifBlank { null } },
            readChapterMarinesCount()
        )
    }

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
            ).also { currentSpaceMarine = it }
        } catch (e: NoSuchElementException) {
            exitProcess(0)
        }
    }
}