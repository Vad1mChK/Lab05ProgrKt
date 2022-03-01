package ru.vad1mchk.progr.lab05.client.util

import ru.vad1mchk.progr.lab05.client.datatypes.SpaceMarine
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*

/**
 * Utility Kotlin object to load a collection on program start
 * and parse it
 */
object CollectionLoader {
    fun load(inputStream: InputStream): LinkedList<SpaceMarine> {
        val list: LinkedList<SpaceMarine> = LinkedList()
        val lines: ArrayList<String> = ArrayList()
        BufferedReader(InputStreamReader(inputStream)).use { br ->
            var line: String?
            while (br.readLine().also { line = it } != null) {
                lines.add(line!!)
                println(line!!.split(Regex(TODO("I need a regex that matches all commas not in quotes"))))
            }
        }
        return list
    }
}