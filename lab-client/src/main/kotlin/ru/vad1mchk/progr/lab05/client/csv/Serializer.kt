package ru.vad1mchk.progr.lab05.client.csv

import ru.vad1mchk.progr.lab05.client.collection.SpaceMarineCollectionManager
import ru.vad1mchk.progr.lab05.client.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.client.util.DateFormatter
import java.io.BufferedWriter
import java.io.File
import java.io.OutputStreamWriter

/**
 * Object to used to serialize the collection
 * stored in memory and save it to a file.
 */
object Serializer {
    lateinit var file: File

    /**
     * Function that saves the collection to a file.
     */
    fun save() {
        val lines = arrayListOf<String>(
            "${DateFormatter.format(SpaceMarineCollectionManager.initializationDate)}${",".repeat(11)}",
            "id,name,coordinateX,coordinateY,creationDate,health,heartCount,loyal,meleeWeapon,chapterName,chapterParentLegion,chapterMarinesCount"
        )
        for (spaceMarine in SpaceMarineCollectionManager) {
            lines.add(serializeMarine(spaceMarine))
        }
        BufferedWriter(OutputStreamWriter(file.outputStream())).use { bufferedWriter ->
            for (line in lines) {
                bufferedWriter.write(line)
                bufferedWriter.newLine()
            }
        }
    }

    /**
     * Wraps a [SpaceMarine] object into a string representation
     * suitable for serialization.
     * @param spaceMarine [SpaceMarine] object to serialize.
     * @return CSV string representation of a [SpaceMarine].
     */
    private fun serializeMarine(spaceMarine: SpaceMarine): String {
        return spaceMarine.toString()
    }
}