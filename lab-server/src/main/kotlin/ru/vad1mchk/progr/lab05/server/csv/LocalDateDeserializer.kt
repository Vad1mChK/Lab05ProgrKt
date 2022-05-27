package ru.vad1mchk.progr.lab05.server.csv

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateDeserializer : StdDeserializer<LocalDate>(LocalDate::class.java) {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): LocalDate {
        return LocalDate.parse(p?.valueAsString, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
    }
}