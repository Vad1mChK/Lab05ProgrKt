package ru.vad1mchk.progr.lab05.server.csv

import ru.vad1mchk.progr.lab05.common.datatypes.Chapter
import ru.vad1mchk.progr.lab05.common.datatypes.Coordinates
import ru.vad1mchk.progr.lab05.common.datatypes.MeleeWeapon
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.common.exceptions.CollectionException
import ru.vad1mchk.progr.lab05.common.exceptions.InvalidDataException
import ru.vad1mchk.progr.lab05.common.exceptions.MalformedElementException
import ru.vad1mchk.progr.lab05.common.io.OutputManager
import ru.vad1mchk.progr.lab05.common.messages.Message
import ru.vad1mchk.progr.lab05.common.util.BooleanParser
import ru.vad1mchk.progr.lab05.common.util.Varargparse
import ru.vad1mchk.progr.lab05.server.util.Configuration
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.nio.file.Files
import java.nio.file.attribute.FileTime
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

/**
 * Class used to deserialize the collection stored in a file and load it.
 */
class CSVDeserializer {
    companion object {
        private const val NORMAL_SIZE: Int = 12
        val stringResources = Configuration.STRING_RESOURCES
    }

    /**
     * Function that loads the collection from a file and deserializes it.
     * @param file File to read the collection from.
     */
    fun load(file: File) {
        val collectionManager = Configuration.COLLECTION_MANAGER
        collectionManager.initializeWithDate(
            try {
                LocalDateTime.ofInstant(
                    (Files.getAttribute(file.toPath(), "creationTime") as FileTime).toInstant(),
                    ZoneId.systemDefault()
                ).toLocalDate()
            } catch (_: UnsupportedOperationException) {
                LocalDate.now()
            }
        )

        var currentLine: String? = ""
        BufferedReader(InputStreamReader(file.inputStream())).use { bufferedReader ->
            readLine()
            while (currentLine != null) {
                currentLine = bufferedReader.readLine()
                if (!currentLine.isNullOrBlank()) {
                    try {
                        makeOf(currentLine!!).also { if (it.validate()) collectionManager.addById(it.id, it) }
                    } catch (e: CollectionException) {
                        OutputManager.sayException(e)
                    }
                }
            }
        }
    }

    /**
     * Parses a CSV string, attempting to make a space marine out of it.
     * @param string String to parse.
     * @return A new space marine if everything went correctly.
     * @throws MalformedElementException if number of elements in the CSV string does not match [NORMAL_SIZE].
     * @throws InvalidDataException if some elements cannot be parsed correctly.
     */
    @Throws(MalformedElementException::class, InvalidDataException::class)
    private fun makeOf(string: String): SpaceMarine {
        val list = Varargparse.splitString(string, ',').map { it.ifEmpty { null } }
        if (list.size != NORMAL_SIZE) {
            throw MalformedElementException(stringResources.getString("MalformedElementException"))
        }
        val id = try {
            list[0]!!.toInt()
        } catch (e: NumberFormatException) {
            throw InvalidDataException(stringResources.getString("InvalidDataException numberFormat"))
        } catch (e: NullPointerException) {
            throw InvalidDataException(stringResources.getString("InvalidDataException notNull"))
        }
        try {
            return SpaceMarine(
                list[1]!!,
                Coordinates(
                    list[2]!!.toInt(),
                    list[3]!!.toFloat()
                ),
                LocalDate.parse(list[4]!!),
                list[5]!!.toDouble(),
                list[6]!!.toLong(),
                BooleanParser.parse(list[7]),
                list[8]?.let { MeleeWeapon.valueOf(it) },
                list[9]?.let {
                    Chapter(
                        it,
                        list[10],
                        list[11]!!.toInt()
                    )
                }
            ).also { it.id = id }
        } catch (e: NullPointerException) {
            throw InvalidDataException(stringResources.getString("InvalidDataException notNull"), e)
        } catch (e: NumberFormatException) {
            throw InvalidDataException(stringResources.getString("InvalidDataException numberFormat"), e)
        } catch (e: IllegalArgumentException) {
            throw InvalidDataException(
                stringResources.getString("InvalidDataException enumConstant").format(MeleeWeapon.listConstants())
            )
        }
    }
}