package ru.vad1mchk.progr.lab05.client.csv

import ru.vad1mchk.progr.lab05.client.collection.SpaceMarineCollectionManager
import ru.vad1mchk.progr.lab05.client.datatypes.Chapter
import ru.vad1mchk.progr.lab05.client.datatypes.Coordinates
import ru.vad1mchk.progr.lab05.client.datatypes.MeleeWeapon
import ru.vad1mchk.progr.lab05.client.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.client.exceptions.CollectionException
import ru.vad1mchk.progr.lab05.client.exceptions.InvalidDataException
import ru.vad1mchk.progr.lab05.client.exceptions.MalformedElementException
import ru.vad1mchk.progr.lab05.client.io.OutputManager
import ru.vad1mchk.progr.lab05.client.messages.Messages
import ru.vad1mchk.progr.lab05.client.util.DateFormatter
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.time.LocalDate
import java.util.regex.Pattern

/**
 * Object used to deserialize the collection
 * stored in a file and load it.
 */
object Deserializer {
    lateinit var file: File

    val csvPattern: Pattern = Pattern.compile(Messages.regexSplitCSVString)

    /**
     * Function that loads the collection from a file and deserializes it.
     */
    fun load() {
        val lines = ArrayList<ArrayList<String?>>()
        var currentLine: String? = ""
        BufferedReader(InputStreamReader(file.inputStream())).use { bufferedReader ->
            while (currentLine != null) {
                currentLine = bufferedReader.readLine()
                if (!currentLine.isNullOrBlank()) {
                    lines.add(splitString(currentLine!!))
                }
            }
        }
        if (lines.isEmpty()) {
            SpaceMarineCollectionManager.initialize(LocalDate.now())
            OutputManager.sayWarning(Messages.warningUnspecifiedInitializationDate)
            return
        }
        try {
            lines[0][0]?.let { DateFormatter.parse(it) }?.let { SpaceMarineCollectionManager.initialize(it) }
            lines.removeFirst()
        } catch (e: InvalidDataException) {
            OutputManager.sayException(e)
            SpaceMarineCollectionManager.initialize(LocalDate.now())
        }
        val normalSize = lines.removeFirst().size
        for (line in lines) {
            try {
                if (line.size != normalSize) {
                    throw MalformedElementException(Messages.exceptionMalformedElement)
                }
                val id = try {
                    line[0]!!.toInt()
                } catch (e: NumberFormatException) {
                    throw InvalidDataException(Messages.exceptionDataInvalidNumberFormat)
                } catch (e: NullPointerException) {
                    throw InvalidDataException(Messages.exceptionDataInvalidNotNull)
                }
                try {
                    SpaceMarine(
                        line[1]!!,
                        Coordinates(
                            line[2]!!.toInt(),
                            line[3]!!.toFloat()
                        ),
                        DateFormatter.parse(line[4]!!),
                        line[5]!!.toDouble(),
                        line[6]!!.toLong(),
                        line[7].toBoolean(),
                        line[8]?.let { MeleeWeapon.valueOf(it) },
                        Chapter(
                            line[9]!!,
                            line[10],
                            line[11]!!.toInt()
                        )

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
            } catch (e: CollectionException) {
                OutputManager.sayException(e)
            }
        }
    }

    /**
     * Splits the CSV string, using comma (`,`) as a
     * delimiter
     * @param csvString
     * @return [ArrayList] of nullable strings
     */
    private fun splitString(csvString: String): ArrayList<String?> {
        return ArrayList(csvString.split(csvPattern).map { it.replace("\",\"", ",").trim().ifBlank { null } })
    }
}