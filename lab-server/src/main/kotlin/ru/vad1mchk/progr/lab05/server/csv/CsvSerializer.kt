package ru.vad1mchk.progr.lab05.server.csv

import com.fasterxml.jackson.databind.ObjectWriter
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.csv.CsvGenerator
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import ru.vad1mchk.progr.lab05.common.util.SpaceMarineComparator
import ru.vad1mchk.progr.lab05.server.util.Configuration
import java.io.File
import java.util.*

/**
 * Class that is responsible for collection serialization in the CSV format. Uses the tools of the Jackson library to
 * perform the process.
 */
class CsvSerializer(filePath: String) {
    private val file: File
    private val csvMapper: CsvMapper
    private val csvSchema: CsvSchema
    private val objectWriter: ObjectWriter

    init {
        file = File(filePath)
        csvMapper = CsvMapper().also {
            it.apply {
                registerModule(JavaTimeModule().also { module ->
                    module.addSerializer(LocalDateSerializer())
                })
                configure(CsvGenerator.Feature.ALWAYS_QUOTE_STRINGS, false)
                configure(CsvGenerator.Feature.STRICT_CHECK_FOR_QUOTING, true)
                configure(SerializationFeature.INDENT_OUTPUT, true)
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
        }.build().withHeader()
        objectWriter = csvMapper.writer(csvSchema)
    }

    /**
     * Writes the collection to the file and saves it.
     * @param file File to write.
     */
    fun writeAllToFile() {
        val sequenceWriter = objectWriter.writeValues(file)
        Configuration.COLLECTION_MANAGER
            .stream()
            .sorted(SpaceMarineComparator(Locale("ru", "RU")))
            .forEach {
            sequenceWriter.write(FlatSpaceMarine.from(it))
        }
        sequenceWriter.flush()
    }
}