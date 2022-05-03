package ru.vad1mchk.progr.lab05.server.csv

import ru.vad1mchk.progr.lab05.server.util.Configuration
import java.io.BufferedWriter
import java.io.File
import java.io.OutputStreamWriter

/**
 * Class used to serialize the collection stored in memory and save it to a file.
 */
class CSVSerializer(private val file: File) {
    companion object {
        val stringResources = Configuration.STRING_RESOURCES
    }

    /**
     * Function that saves the collection to a file.
     */
    fun save() {
        val collectionManager = Configuration.COLLECTION_MANAGER
        BufferedWriter(OutputStreamWriter(file.outputStream())).use { bufferedWriter ->
            bufferedWriter.write(stringResources.getString("collection header"))
            bufferedWriter.newLine()
            collectionManager.collection().forEach { spaceMarine ->
                bufferedWriter.write(spaceMarine.toString())
                bufferedWriter.newLine()
            }
        }
    }
}