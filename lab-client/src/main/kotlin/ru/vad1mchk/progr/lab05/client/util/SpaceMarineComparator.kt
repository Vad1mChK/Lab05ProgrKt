package ru.vad1mchk.progr.lab05.client.util

import ru.vad1mchk.progr.lab05.client.datatypes.SpaceMarine
import java.text.Collator
import java.util.*
import kotlin.Comparator

/**
 * A comparator that compares the instances of [SpaceMarine] using locale-sensitive comparison.
 */
object SpaceMarineComparator: Comparator<SpaceMarine> {
    override fun compare(left: SpaceMarine, right: SpaceMarine): Int {
        /* For now, the collator is hardcoded to be Russian, but in the 8th lab, support for different locales will be
        added.
         */
        val collator = Collator.getInstance(Locale("ru", "RU"))
        return collator.compare(left.name, right.name).also {
            if (it == 0) return compareValuesBy(
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