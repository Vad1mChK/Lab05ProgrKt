package ru.vad1mchk.progr.lab05.client.strings

import javafx.beans.binding.Bindings
import javafx.beans.binding.StringBinding
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.ChangeListener
import java.text.Collator
import java.text.MessageFormat
import java.util.*
import java.util.concurrent.Callable

object StringPropertyManager {
    const val bundleName = "strings"
    val supportedLocales: Map<Locale, Strings> = Collections.unmodifiableMap(hashMapOf(
        Locale("ru", "RU") to Strings_ru_RU,
        Locale("is", "IS") to Strings_is_IS,
        Locale("uk", "UA") to Strings_uk_UA,
        Locale("es", "NI") to Strings_es_NI
    ).toSortedMap { left, right -> compareValuesBy(left, right, Locale::getLanguage, Locale::getCountry) })

    init {
        if (defaultLocale() !in supportedLocales.keys) {
            Locale.setDefault(supportedLocales.keys.first())
        }
    }

    private val localeProperty = SimpleObjectProperty(defaultLocale())

    fun defaultLocale(): Locale {
        return Locale.getDefault()
    }

    fun getLocale(): Locale {
        return localeProperty.get()
    }

    fun setLocale(locale: Locale) {
        localeProperty.set(locale)
    }

    operator fun get(key: String, vararg args: Any): String {
        return MessageFormat.format(
            supportedLocales[getLocale()]!!.getString(key),
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