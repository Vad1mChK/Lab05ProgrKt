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
import java.io.Reader
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Object class to deserialize the collection stored in CSV file.
 */
object Deserializer {
    /**
     * The file variable
     */
    lateinit var file: File

    /**
     * Reads the collection file and automatically passes it to
     * collection manager
     */
    fun readFile() {
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
                Messages.say(e.message!!, level = Messages.Level.ERROR)
            }
        }
    }

    /**
     * Splits the line into multiple ones using comma as a delimiter,
     * treating the escaped commas (almost) correctly. Empty strings
     * will become null
     *
     * @param line Line to split
     * @return Array list of strings?
     */
    private fun splitPrimary(line: String): ArrayList<String?> {
        val array = ArrayList<String?>()
        for (word in line.split(Regex(",(?=([^\"]*\"[^\"]*\")*[^\"]*\$)"))) {
            array.add(if (word.isNotEmpty()) word.replace("\",\"", ",") else null)
        }
        return array
    }

    /**
     * Splits the line into multiple anes using the secondary delimiter
     * (colon ':')
     * It is used to split fields (Coordinates and Chapter) into subfields.
     * Backslash '\' is used to escape the colon
     *
     * @param line Line to split
     * @return Array list of strings?
     */
    private fun splitSecondary(line: String): ArrayList<String?> {
        val array = ArrayList<String?>()
        for (word in line.reversed().split(Regex(":(?=(\\\\\\\\)*+[^\\\\])"))) {
            array.add(0, if (word.isNotEmpty()) word.replace(":\\", ":").reversed() else null)
        }
        return array
    }

    /**
     * Parses the local date if it matches the format:
     * "dd.MM.yyyy", where "dd" - day, "MM" - month, "yyyy" - year
     * @param dateString string representation of date
     * @return local date
     * @throws InvalidFieldFormatException if the string does not
     * match the date format
     */
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

    /**
     * Parses the coordinates represented by int and float values split
     * by colon ':', like "x:y"
     *
     * @param coordinatesString string representation of coordinates
     * @return coordinates
     * @throws InvalidFieldFormatException if the coordinates do not match the format
     * @throws InvalidFieldTypeException if the coordinates are not of correct type
     */
    private fun parseCoordinates(coordinatesString: String): Coordinates {
        if (!coordinatesString.matches(Regex("^-?\\d+:-?(\\d+\\.\\d*|\\d*\\.\\d+|\\d*)\$"))) {
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

    /**
     * Parses melee weapon as an enum constant
     * @param meleeWeaponString string representation of melee weapon
     * @return melee weapon if the string is not null, else null
     * @throws InvalidEnumConstantException if the string does not
     * correspond to a MeleeWeapon constant
     */
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

    /**
     * Parses chapter represented by a string delimited by colon
     * @param chapterString string representation of a chapter
     * @return chapter the string is not null, else null
     * @throws InvalidFieldValueException if the chapter name is null or empty,
     * or if the marine count is null
     * @throws InvalidFieldTypeException if marine count is not an int
     */
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