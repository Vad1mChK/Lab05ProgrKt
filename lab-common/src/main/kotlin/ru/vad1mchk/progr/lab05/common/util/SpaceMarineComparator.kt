package ru.vad1mchk.progr.lab05.common.util

import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import java.text.Collator
import java.util.*

/**
 * Compares two instances of [SpaceMarine] by their fields in order:
 * name, coordinates, creation date, health, heart count, melee
 * weapon, chapter and id. The string comparison is based on the
 * [java.text.Collator] functionality and depends on the given [locale].
 *
 * @property locale Locale to use for collation.
 */
class SpaceMarineComparator(private val locale: Locale = Locale.ROOT) : Comparator<SpaceMarine> {
    private val chapterComparator = ChapterComparator(locale)
    private val collator = Collator.getInstance(locale)
    override fun compare(left: SpaceMarine?, right: SpaceMarine?): Int {
        return Comparator
            .comparing(SpaceMarine::name, collator)
            .thenComparing(SpaceMarine::coordinates)
            .thenComparing(SpaceMarine::creationDate)
            .thenComparing(SpaceMarine::health)
            .thenComparing(SpaceMarine::heartCount)
            .thenComparing(SpaceMarine::loyal)
            .thenComparing(SpaceMarine::meleeWeapon, nullsFirst())
            .thenComparing(SpaceMarine::chapter, nullsFirst(chapterComparator))
            .compare(left, right)
    }
}