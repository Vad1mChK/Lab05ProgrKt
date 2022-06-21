package ru.vad1mchk.progr.lab05.client.util

import javafx.util.StringConverter
import ru.vad1mchk.progr.lab05.client.strings.StringPropertyManager
import ru.vad1mchk.progr.lab05.common.datatypes.MeleeWeapon

class MeleeWeaponConverter: StringConverter<MeleeWeapon>() {
    override fun toString(meleeWeapon: MeleeWeapon?): String? {
        if (meleeWeapon == null) return null
        return StringPropertyManager[
                "propertyValueMeleeWeapon" + meleeWeapon
                    .name
                    .lowercase()
                    .split("_")
                    .map { it.first().uppercase() + it.substring(1)}
                    .joinToString("")
        ]
    }

    override fun fromString(string: String?): MeleeWeapon? {
        return null
    }
}