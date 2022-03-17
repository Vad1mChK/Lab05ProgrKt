package ru.vad1mchk.progr.lab05.client.util

import ru.vad1mchk.progr.lab05.client.datatypes.Chapter
import ru.vad1mchk.progr.lab05.client.datatypes.Coordinates
import ru.vad1mchk.progr.lab05.client.datatypes.MeleeWeapon
import ru.vad1mchk.progr.lab05.client.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.client.exceptions.*
import ru.vad1mchk.progr.lab05.client.manager.SpaceMarineCollectionManager
import ru.vad1mchk.progr.lab05.client.messages.Messages
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Deserializer {
    lateinit var file: File

    fun read() {
        val lines = ArrayList<ArrayList<String?>>()
        BufferedReader(FileReader(file)).let { reader ->
            var currentLine: String?
            do {
                currentLine = reader.readLine().also {
                    if (it != null) {
                        lines.add(splitPrimary(it))
                    }
                }
            } while (currentLine != null)
        }
        if (lines.isNotEmpty()) {
            try {
                SpaceMarineCollectionManager.initializationDate = parseDate(lines[0][0]!!)
            } catch (e: Exception) {
                if (e is InvalidFieldFormatException || e is NullPointerException) {
                    SpaceMarineCollectionManager.initializationDate = LocalDate.now()
                } else {
                    throw e
                }
            }
        }
        for (i in (2 until lines.size)) {
            try {
                SpaceMarineCollectionManager.addWithId(
                    lines[i][0]!!.toInt(), SpaceMarine(
                        lines[i][1]!!,
                        parseCoordinates(lines[i][2]!!),
                        parseDate(lines[i][3]!!),
                        lines[i][4]!!.toDouble(),
                        lines[i][5]!!.toLong(),
                        lines[i][6].toBoolean(),
                        parseMeleeWeapon(lines[i][7]),
                        parseChapter(lines[i][8])
                    )
                )
            } catch (e: IDCollisionException) {
                Messages.say(Messages.Level.ERROR, e.message!!)
            }
        }
    }

    private fun splitPrimary(line: String): ArrayList<String?> {
        val array = ArrayList<String?>()
        for (word in line.split(Regex(",(?=([^\"]*\"[^\"]*\")*[^\"]*\$)"))) {
            array.add(if (word.isNotEmpty()) word.replace("\",\"", ",") else null)
        }
        return array
    }

    private fun splitSecondary(line: String): ArrayList<String?> {
        val array = ArrayList<String?>()
        for (word in line.reversed().split(Regex(":(?=(\\\\\\\\)*+[^\\\\])"))) {
            array.add(0, if (word.isNotEmpty()) word.replace(":\\", ":").reversed() else null)
        }
        return array
    }

    private fun parseDate(dateString: String): LocalDate {
        if (!dateString.matches(
                Regex(
                    "^((([0-2]\\d|3[01])\\.(01|03|05|07|08|10|12)|([0-2]\\d|30)\\.(04|06|09|11)|([01]\\d|2[0-8])\\.02)\\.\\d{4})|(29\\.02\\.\\d{2}(0[48]|[2468][048]|[13579][26]))|(29\\.02\\.(0[48]|[2468][048]|[13579][26])00)$"
                )
            )
        ) {
            throw InvalidFieldFormatException(Messages["errorInvalidFieldFormatDate"])
        }
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
    }

    private fun parseCoordinates(coordinatesString: String): Coordinates {
        if (!coordinatesString.matches(Regex("^-?\\d+:-?(\\d+\\.\\d*|\\d*\\.\\d+)\$"))) {
            throw InvalidFieldFormatException(Messages["errorInvalidFieldFormatCoordinates"])
        }
        splitSecondary(coordinatesString).let {
            try {
                Coordinates(it[0]!!.toInt(), it[1]!!.toFloat()).let { coordinates ->
                    return coordinates
                }
            } catch (e: NumberFormatException) {
                throw InvalidFieldTypeException(Messages["errorInvalidFieldTypeCoordinates"], e)
            }
        }
    }

    private fun parseMeleeWeapon(meleeWeaponString: String?): MeleeWeapon? {
        if (meleeWeaponString == null) return null
        try {
            return MeleeWeapon.valueOf(meleeWeaponString)
        } catch (e: IllegalArgumentException) {
            throw InvalidEnumConstantException(
                String.format(
                    Messages["errorInvalidEnumConstant"],
                    ArrayList(MeleeWeapon.values().map { it.name }).joinToString(", ", "{", "}"),
                )
            )
        }
    }

    private fun parseChapter(chapterString: String?): Chapter? {
        if (chapterString == null) return null
        splitSecondary(chapterString).let {
            if (it[0].isNullOrEmpty()) {
                throw InvalidFieldValueException(Messages["errorInvalidFieldValueChapterName"])
            }
            try {
                it[2]?.toInt()
            } catch (e: NumberFormatException) {
                throw InvalidFieldTypeException(Messages["errorInvalidFieldTypeChapterMarineCount"], e)
            } catch (e: NullPointerException) {
                throw InvalidFieldValueException(Messages["errorInvalidFieldValueChapterMarineCountNull"], e)
            }
            return Chapter(it[0]!!, it[1], it[2]!!.toInt())
        }
    }
}