package ru.vad1mchk.progr.lab05.client.strings

import javafx.beans.binding.Bindings
import javafx.beans.binding.StringBinding
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.ChangeListener
import java.text.Collator
import java.text.DateFormat
import java.text.MessageFormat
import java.text.NumberFormat
import java.time.LocalDate
import java.sql.Date
import java.util.*
import java.util.concurrent.Callable
import kotlin.collections.HashMap

object StringPropertyManager {
    const val bundleName = "strings"
    val supportedLocales: Map<Locale, Strings> = Collections.unmodifiableMap(hashMapOf(
        Locale("ru", "RU") to Strings_ru_RU,
        Locale("is", "IS") to Strings_is_IS,
        Locale("uk", "UA") to Strings_uk_UA,
        Locale("es", "NI") to Strings_es_NI
    ).toSortedMap { left, right -> compareValuesBy(left, right, Locale::getLanguage, Locale::getCountry) })

    private val dateFormats = HashMap<Locale, DateFormat>()
    val dateFormat: DateFormat
        get() = dateFormats[locale]!!

    private val numberFormats = HashMap<Locale, NumberFormat>()
    val numberFormat: NumberFormat
        get() = numberFormats[locale]!!

    private val integerFormats = HashMap<Locale, NumberFormat>()
    val integerFormat: NumberFormat
        get() = integerFormats[locale]!!

    val localeProperty = SimpleObjectProperty(defaultLocale())
    var locale: Locale
        get() = localeProperty.get()
        set(newLocale) = localeProperty.set(newLocale)

    init {
        if (defaultLocale() !in supportedLocales.keys) {
            Locale.setDefault(supportedLocales.keys.first())
        }
        for (locale in supportedLocales.keys) {
            dateFormats[locale] = DateFormat.getDateInstance(DateFormat.FULL, locale)
            numberFormats[locale] = NumberFormat.getNumberInstance(locale)
            integerFormats[locale] = NumberFormat.getIntegerInstance(locale)
        }
    }

    fun defaultLocale(): Locale {
        return Locale.getDefault()
    }

    operator fun get(key: String, vararg args: Any): String {
        return MessageFormat.format(
            supportedLocales[locale]!!.getString(key),
            *args
        )
    }

    fun createBinding(key: String, vararg args: Any): StringBinding {
        return Bindings.createStringBinding({ get(key, *args) }, localeProperty)
    }

    fun createBinding(func: Callable<String>): StringBinding {
        return Bindings.createStringBinding(func, localeProperty)
    }
}