package ru.vad1mchk.progr.lab05.client.util

import ru.vad1mchk.progr.lab05.client.datatypes.SpaceMarine
import java.text.Collator
import java.util.*

/**
 * Object that compares two instances of [SpaceMarine] according to some special rules. Does not allow `null` values.
 */
object SpaceMarineComparator : Comparator<SpaceMarine> {
    override fun compare(left: SpaceMarine, right: SpaceMarine): Int {
        val collator = Collator.getInstance(Locale("ru", "RU"))
        return collator.compare(left, right).also {
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