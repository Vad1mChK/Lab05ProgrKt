package ru.vad1mchk.progr.lab05.common.util

import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import java.text.Collator
import java.util.*

/**
 * Comparator that compares instances of [SpaceMarine] using a collator of the current locale. The comparison is in the
 * following order: `name`, `coordinates`, `creationDate`, `health`, `heartCount`, `loyal`, `meleeWeapon`, `chapter`,
 * `id`.
 * @property Locale to use for string collation.
 */
class SpaceMarineComparator(val desiredLocale: Locale) : Comparator<SpaceMarine> {
    override fun compare(left: SpaceMarine?, right: SpaceMarine?): Int {
        if (left == null || right == null) return compareValues(left, right)
        val collator = Collator.getInstance(desiredLocale)
        return Comparator
            .comparing(SpaceMarine::name, collator)
            .thenComparing(SpaceMarine::coordinates)
            .thenComparing(SpaceMarine::creationDate)
            .thenComparing(SpaceMarine::health)
            .thenComparing(SpaceMarine::heartCount)
            .thenComparing(SpaceMarine::loyal)
            .thenComparing(SpaceMarine::meleeWeapon, nullsFirst())
            .thenComparing(SpaceMarine::chapter, nullsFirst(ChapterComparator(desiredLocale)))
            .thenComparing(SpaceMarine::id)
            .compare(left, right)
    }
}