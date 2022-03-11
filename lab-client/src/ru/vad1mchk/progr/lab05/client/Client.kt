package ru.vad1mchk.progr.lab05.client

import ru.vad1mchk.progr.lab05.client.util.Deserializer
import java.io.File

fun main(args: Array<String>) {
    Deserializer.file = File("collection.csv")
    for (line in Deserializer.read()) {
        println(line)
    }
}