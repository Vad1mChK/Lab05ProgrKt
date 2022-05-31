package ru.vad1mchk.progr.lab05.server.csv

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectReader
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvParser
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import ru.vad1mchk.progr.lab05.common.exceptions.InvalidDataException
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.server.util.Configuration
import java.io.File
import java.time.LocalDate

/**
 * Class that is responsible for collection deserialization in the CSV format. Uses the tools of the Jackson library to
 * perform the process.
 * @property file File to deserialize.
 */
class CsvDeserializer(filePath: String) {
    private val file: File
    private val csvMapper: CsvMapper
    private val csvSchema: CsvSchema
    private val objectReader: ObjectReader

    init {
        file = File(filePath)
        csvMapper = CsvMapper().also {
            it.apply {
                registerModule(JavaTimeModule().also { module ->
                    module.addDeserializer(LocalDate::class.java, LocalDateDeserializer())
                })
                configure(CsvParser.Feature.SKIP_EMPTY_LINES, true)
                configure(CsvParser.Feature.EMPTY_STRING_AS_NULL, true)
                configure(CsvParser.Feature.TRIM_SPACES,true)
                configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true)
            }
        }
        csvSchema = CsvSchema.builder().apply {
            addNumberColumn("id")
            addColumn("name")
            addNumberColumn("coordinatesX")
            addNumberColumn("coordinatesY")
            addColumn("creationDate")
            addNumberColumn("health")
            addNumberColumn("heartCount")
            addBooleanColumn("loyal")
            addColumn("meleeWeapon")
            addColumn("chapterName")
            addColumn("chapterParentLegion")
            addNumberColumn("chapterMarinesCount")
        }.build().withHeader().withStrictHeaders(true)
        objectReader = csvMapper.readerFor(FlatSpaceMarine::class.java).with(csvSchema)
    }

    /**
     * Reads all the objects from the collection file and fills the collection manager.
     */
    fun readAll() {
        val sequenceReader = objectReader.readValues<FlatSpaceMarine>(file)
        sequenceReader
                .readAll()
                .filterNotNull()
                .forEach {
                    try {
                        Configuration.COLLECTION_MANAGER.addPreservingID(it.deflatten())
                    } catch (e: InvalidDataException) {
                        Printer.printError(e)
                    }
                }
    }
}