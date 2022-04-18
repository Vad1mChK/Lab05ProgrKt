package ru.vad1mchk.progr.lab05.common.util

import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import java.text.Collator
import java.util.*

/**
 * Object that compares two instances of [SpaceMarine] according to some special rules. Does not allow `null` values.
 * It is planned to use a collator of the current locale in the future. For now, the locale is hardcoded.
 */
object SpaceMarineComparator : Comparator<SpaceMarine> {
    override fun compare(left: SpaceMarine, right: SpaceMarine): Int {
        val collator = Collator.getInstance(Locale("ru", "RU"))
        return collator.compare(left.name, right.name).also {
            if (it == 0) {
                return compareValuesBy(
                    left,
                    right,
                    SpaceMarine::coordinates,
                    SpaceMarine::creationDate,
                    SpaceMarine::health,
                    SpaceMarine::heartCount,
                    SpaceMarine::loyal,
                    SpaceMarine::meleeWeapon,
                    SpaceMarine::chapter,
                    SpaceMarine::id
                )
            }
        }
    }
}