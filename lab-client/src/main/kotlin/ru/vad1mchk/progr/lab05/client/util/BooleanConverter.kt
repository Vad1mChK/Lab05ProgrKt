package ru.vad1mchk.progr.lab05.client.util

import javafx.util.StringConverter
import ru.vad1mchk.progr.lab05.client.strings.StringPropertyManager

class BooleanConverter: StringConverter<Boolean>() {
    override fun toString(bool: Boolean?): String? {
        return bool?.let {
                b -> StringPropertyManager[
                "propertyValue"+b.toString().let { it.first().uppercase() + it.substring(1) }
                ]
        }
    }

    override fun fromString(string: String?): Boolean? {
        return null
    }
}