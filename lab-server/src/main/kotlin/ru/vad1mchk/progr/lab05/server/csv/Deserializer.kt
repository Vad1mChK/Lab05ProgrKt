package ru.vad1mchk.progr.lab05.server.csv

import ru.vad1mchk.progr.lab05.common.datatypes.Chapter
import ru.vad1mchk.progr.lab05.common.datatypes.Coordinates
import ru.vad1mchk.progr.lab05.common.datatypes.MeleeWeapon
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.common.exceptions.CollectionException
import ru.vad1mchk.progr.lab05.common.exceptions.InvalidDataException
import ru.vad1mchk.progr.lab05.common.exceptions.MalformedElementException
import ru.vad1mchk.progr.lab05.common.io.OutputManager
import ru.vad1mchk.progr.lab05.common.messages.Messages
import ru.vad1mchk.progr.lab05.common.util.BooleanParser
import ru.vad1mchk.progr.lab05.common.util.DateFormatter
import ru.vad1mchk.progr.lab05.server.collection.SpaceMarineCollectionManager
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.nio.file.Files
import java.nio.file.attribute.FileTime
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.regex.Pattern

/**
 * Object used to deserialize the collection stored in a file and load it.
 */
object Deserializer {
    private const val NORMAL_SIZE: Int = 12
    private val csvPattern: Pattern = Pattern.compile(Messages.regexSplitCSVString)

    /**
     * Function that loads the collection from a file and deserializes it.
     * @param file File to read the collection from.
     */
    fun load(file: File) {
        var currentLine: String? = ""
        SpaceMarineCollectionManager.initialize(
            LocalDateTime.ofInstant(
                (Files.getAttribute(file.toPath(), "creationTime") as FileTime).toInstant(),
                ZoneId.systemDefault()
            ).toLocalDate()
        )
        BufferedReader(InputStreamReader(file.inputStream())).use { bufferedReader ->
            readLine()
            while (currentLine != null) {
                currentLine = bufferedReader.readLine()
                if (!currentLine.isNullOrBlank()) {
                    try {
                        SpaceMarineCollectionManager.add(
                            makeOf(splitString(currentLine!!))
                        )
                    } catch (e: CollectionException) {
                        OutputManager.sayException(e)
                    }
                }
            }
        }
    }

    /**
     * Parses an array of nullable strings, attempting to make a space marine out of it.
     * @param array [ArrayList] of strings made by csv.
     * @param normalSize Normal array size to compare with.
     * @return
     */
    @Throws(MalformedElementException::class, InvalidDataException::class)
    private fun makeOf(array: ArrayList<String?>): SpaceMarine {
        if (array.size != NORMAL_SIZE) {
            throw MalformedElementException(Messages.exceptionMalformedElement)
        }
        val id = try {
            array[0]!!.toInt()
        } catch (e: NumberFormatException) {
            throw InvalidDataException(Messages.exceptionDataInvalidNumberFormat)
        } catch (e: NullPointerException) {
            throw InvalidDataException(Messages.exceptionDataInvalidNotNull)
        }
        try {
            return SpaceMarine(
                array[1]!!,
                Coordinates(
                    array[2]!!.toInt(),
                    array[3]!!.toFloat()
                ),
                DateFormatter.parse(array[4]!!),
                array[5]!!.toDouble(),
                array[6]!!.toLong(),
                BooleanParser.parse(array[7]),
                array[8]?.let { MeleeWeapon.valueOf(it) },
                array[9]?.let {
                    Chapter(
                        it,
                        array[10],
                        array[11]!!.toInt()
                    )
                }

            ).also {
                if (it.validate()) {
                    SpaceMarineCollectionManager.addById(
                        id, it
                    )
                }
            }
        } catch (e: NullPointerException) {
            throw InvalidDataException(Messages.exceptionDataInvalidNotNull, e)
        } catch (e: NumberFormatException) {
            throw InvalidDataException(Messages.exceptionDataInvalidNumberFormat, e)
        } catch (e: IllegalArgumentException) {
            throw InvalidDataException(
                String.format(
                    Messages.exceptionDataInvalidEnumConstant,
                    MeleeWeapon.listConstants()
                )
            )
        }
    }

    /**
     * Splits the CSV string, using comma (`,`) as a delimiter
     * @param csvString The comma-separated string to split.
     * @return [ArrayList] of nullable strings.
     */
    private fun splitString(csvString: String): ArrayList<String?> {
        return ArrayList(csvString.split(csvPattern).map {
            it.replace("\",\"", ",").replace("\"\"", "\"").trim().ifBlank { null }
        })
    }
}