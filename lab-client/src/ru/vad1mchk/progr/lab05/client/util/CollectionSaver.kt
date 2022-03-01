package ru.vad1mchk.progr.lab05.client.util

import ru.vad1mchk.progr.lab05.client.datatypes.SpaceMarine
import java.io.BufferedWriter
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.util.*

/**
 * Utility Kotlin object to save the collection on command
 * or after loading it
 */
object CollectionSaver {
    fun save(collection: LinkedList<SpaceMarine>, outputStream: FileOutputStream) {
        val fields = SpaceMarine::class.java.declaredFields
        BufferedWriter(OutputStreamWriter(outputStream)).use { bw ->
            val spaceMarineFieldNames = ArrayList<String>()
            for (field in fields) {
                if (field.isAnnotationPresent(DataField::class.java))
                    spaceMarineFieldNames.add(field.name)
            }
            spaceMarineFieldNames.joinToString(",")
            bw.write(spaceMarineFieldNames.joinToString(","))
        }
    }

    /**
     * Escapes the comma in given string by using quotes, like so:
     *
     * var,arg,parse -> var","arg","parse
     *
     * Also, if the string is null, turns it into empty string
     *
     * @param string Nullable string to escape
     *
     * @return an escaped string, fully safe to put into a CSV file
     */
    fun escape(string: String?): String {
        if (string == null) {
            return ""
        }
        return string.replace(",", "\",\"")
    }
}