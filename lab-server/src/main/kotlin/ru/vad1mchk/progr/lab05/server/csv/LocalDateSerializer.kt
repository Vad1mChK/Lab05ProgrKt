package ru.vad1mchk.progr.lab05.server.csv

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Class to provide a custom way of serializing [LocalDate] to a string.
 */
class LocalDateSerializer : StdSerializer<LocalDate>(LocalDate::class.java) {
    override fun serialize(value: LocalDate?, gen: JsonGenerator?, provider: SerializerProvider?) {
        gen?.writeString(value?.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
    }
}