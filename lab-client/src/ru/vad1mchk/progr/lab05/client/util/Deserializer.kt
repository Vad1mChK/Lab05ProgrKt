package ru.vad1mchk.progr.lab05.client.util

import ru.vad1mchk.progr.lab05.client.exceptions.InvalidFieldFormatException
import ru.vad1mchk.progr.lab05.client.messages.Messages
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.time.LocalDate

object Deserializer {
    lateinit var file: File

    val readResults = read()

    fun read(): ArrayList<ArrayList<String?>> {
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
        return lines
    }

    private fun splitPrimary(line: String): ArrayList<String?> {
        val array = ArrayList<String?>()
        for (word in line.split(Regex(",(?=([^\"]*\"[^\"]*\")*[^\"]*\$)"))) {
            array.add(if (word.isNotEmpty()) word.replace("\",\"", ",") else null)
        }
        return array
    }

    private fun splitSecondary(line: String): ArrayList<String> {
        val array = ArrayList<String>()
        for (word in line.split(Regex("(?<!\\\\):"))) {
            array.add(word.replace("\\:", ":"))
        }
        return array
    }

    private fun parseDate(dateString: String): LocalDate {
        if (!dateString.matches(Regex(
                TODO("Need a regex that validates date")
            ))) {
            throw InvalidFieldFormatException(Messages["invalidFieldFormatDate"])
        }
    }
}