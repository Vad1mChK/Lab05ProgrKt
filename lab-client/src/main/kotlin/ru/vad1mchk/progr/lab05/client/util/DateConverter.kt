package ru.vad1mchk.progr.lab05.client.util

import javafx.util.StringConverter
import ru.vad1mchk.progr.lab05.client.strings.StringPropertyManager
import java.sql.Date
import java.time.LocalDate

class DateConverter: StringConverter<LocalDate>() {
    override fun toString(localDate: LocalDate?): String? {
        return localDate?.let { StringPropertyManager.dateFormat.format(Date.valueOf(localDate)) }
    }

    override fun fromString(string: String?): LocalDate? {
        return null
    }
}