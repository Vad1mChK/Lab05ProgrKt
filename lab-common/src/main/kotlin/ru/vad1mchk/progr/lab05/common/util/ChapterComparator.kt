package ru.vad1mchk.progr.lab05.common.util

import ru.vad1mchk.progr.lab05.common.datatypes.Chapter
import java.text.Collator
import java.util.*

/**
 * Comparator that compares instances of [Chapter] using a collator of the current locale. The comparison is in the
 * following order: `name`, `parentLegion`, `marinesCount`.
 * @property desiredLocale Locale to use for string collation.
 */
class ChapterComparator(private val desiredLocale: Locale) : Comparator<Chapter> {
    override fun compare(left: Chapter?, right: Chapter?): Int {
        if (left == null || right == null) return compareValues(left, right)
        val collator = Collator.getInstance(desiredLocale)
        return Comparator
            .comparing(Chapter::name, collator)
            .thenComparing(Chapter::parentLegion, nullsFirst(collator))
            .thenComparing(Chapter::marinesCount)
            .compare(left, right)
    }
}