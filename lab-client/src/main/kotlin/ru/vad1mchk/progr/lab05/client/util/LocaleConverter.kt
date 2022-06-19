package ru.vad1mchk.progr.lab05.client.util

import javafx.util.StringConverter
import java.util.Locale

class LocaleConverter: StringConverter<Locale>() {
    override fun toString(locale: Locale): String {
        return locale.getDisplayName(locale)
    }

    override fun fromString(string: String): Locale {
        return Locale.getDefault()
    }
}