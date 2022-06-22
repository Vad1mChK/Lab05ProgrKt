package ru.vad1mchk.progr.lab05.client.util

import ru.vad1mchk.progr.lab05.client.strings.StringPropertyManager
import ru.vad1mchk.progr.lab05.common.datatypes.MeleeWeapon
import java.util.*
import java.util.concurrent.Callable

object CustomChoiceFormat {
    fun formatByRules(callable: Callable<String>): String {
        return callable.call()
    }

    fun formatBoolean(bool: Boolean?): String {
        return bool?.let {
                b -> StringPropertyManager[
                "propertyValue"+b.toString().let { it.first().uppercase() + it.substring(1) }
                ]
        } ?: StringPropertyManager["propertyValueNull"]
    }

    fun formatMeleeWeapon(meleeWeapon: MeleeWeapon?): String {
        if (meleeWeapon == null) return StringPropertyManager["propertyValueNull"]
        return StringPropertyManager[
                "propertyValueMeleeWeapon" + meleeWeapon
                    .name
                    .lowercase()
                    .split("_")
                    .map { it.first().uppercase() + it.substring(1)}
                    .joinToString("")
        ]
    }

}