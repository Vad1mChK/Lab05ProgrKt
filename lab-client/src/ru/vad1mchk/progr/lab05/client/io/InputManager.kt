package ru.vad1mchk.progr.lab05.client.io

import ru.vad1mchk.progr.lab05.client.commands.Command
import ru.vad1mchk.progr.lab05.client.datatypes.*
import java.io.Reader

interface InputManager {
    fun readName(): String

    fun readCoordinateX(): Int

    fun readCoordinateY(): Float

    fun readCoordinates(): Coordinates

    fun readHealth(): Double

    fun readHeartCount(): Long

    fun readLoyal(): Boolean

    fun readMeleeWeapon(): MeleeWeapon?

    fun readChapterName(): String

    fun readChapterParentLegion(): String?

    fun readChapterMarinesCount(): Int

    fun readChapter(): Chapter?

    fun readSpaceMarine(): SpaceMarine

    fun readCommand(): Command
}