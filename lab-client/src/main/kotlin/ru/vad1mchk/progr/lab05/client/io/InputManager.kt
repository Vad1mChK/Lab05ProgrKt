package ru.vad1mchk.progr.lab05.client.io

import ru.vad1mchk.progr.lab05.client.command.CommandWrapper
import ru.vad1mchk.progr.lab05.client.datatypes.Chapter
import ru.vad1mchk.progr.lab05.client.datatypes.Coordinates
import ru.vad1mchk.progr.lab05.client.datatypes.MeleeWeapon
import ru.vad1mchk.progr.lab05.client.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.client.exceptions.InvalidDataException
import java.util.*

/**
 * Base interface for all input managers.
 */
interface InputManager {

    /**
     * Reads `name` of [SpaceMarine] from input.
     * @return Name of [SpaceMarine] as a string.
     * @throws InvalidDataException if the name is null.
     */
    @Throws(InvalidDataException::class)
    fun readName(): String

    /**
     * Reads `x` coordinate of [Coordinates] from input.
     * @return `x` coordinate as an int.
     * @throws InvalidDataException if the coordinate is less or equal to [Coordinates.MIN_X] or if it cannot be parsed.
     */
    @Throws(InvalidDataException::class)
    fun readCoordinateX(): Int

    /**
     * Reads `y` coordinate of [Coordinates] from input.
     * @return `y` coordinate as a float.
     * @throws InvalidDataException if the coordinate cannot be parsed.
     */
    @Throws(InvalidDataException::class)
    fun readCoordinateY(): Float

    /**
     * Reads [Coordinates] from input.
     * @return Coordinates.
     */
    fun readCoordinates(): Coordinates

    /**
     * Reads `health` of [SpaceMarine] from input.
     * @return `health` as a double.
     * @throws InvalidDataException if `health` is less than [SpaceMarine.MIN_HEALTH] cannot be parsed.
     */
    @Throws(InvalidDataException::class)
    fun readHealth(): Double

    /**
     * Reads `heartCount` of [SpaceMarine] from input.
     * @return `heartCount` as a long.
     * @throws InvalidDataException if `heartCount` is not within range ([SpaceMarine.MIN_HEART_COUNT]..
     * [SpaceMarine.MAX_HEART_COUNT]) or if it cannot be parsed.
     */
    @Throws(InvalidDataException::class)
    fun readHeartCount(): Long

    /**
     * Reads `loyal` of [SpaceMarine] from input.
     * @return `heartCount` as a boolean.
     * @throws InvalidDataException if `loyal` cannot be parsed.
     */
    @Throws(InvalidDataException::class)
    fun readLoyal(): Boolean

    /**
     * Reads `meleeWeapon` of [SpaceMarine] from input.
     * @return `meleeWeapon` as a [MeleeWeapon].
     * @throws InvalidDataException if it is not a valid enum constant.
     */
    fun readMeleeWeapon(): MeleeWeapon?

    /**
     * Reads `name` of [Chapter] from input.
     * @return `name` as a string. If it is null, the entire [Chapter] field becomes null.
     */
    fun readChapterName(): String?

    /**
     * Reads `parentLegion` of [Chapter] from input.
     * @return `parentLegion` as a nullable string.
     */
    fun readChapterParentLegion(): String?

    /**
     * Reads `marinesCount` of [Chapter] from input.
     * @return `marinesCount` as an int.
     * @throws InvalidDataException if `marinesCount` is not within range ([Chapter.MIN_MARINES_COUNT] ..
     * [Chapter.MAX_MARINES_COUNT]) or if it cannot be parsed.
     */
    @Throws(InvalidDataException::class)
    fun readChapterMarinesCount(): Int

    /**
     * Reads [Chapter] from input.
     * @return Nullable chapter.
     */
    fun readChapter(): Chapter?

    /**
     * Reads [SpaceMarine] from input.
     * @return A [SpaceMarine] object.
     */
    fun readSpaceMarine(): SpaceMarine

    /**
     * Reads command name and argument pair from input.
     * @return The command wrapper.
     */
    fun readCommand(): CommandWrapper

    /**
     * Gets the scanner currently used by this implementation of the interface.
     * @return Scanner of the manager.
     */
    fun scanner(): Scanner
}