package ru.vad1mchk.progr.lab05.common.util

import ru.vad1mchk.progr.lab05.common.datatypes.Chapter
import java.text.Collator
import java.util.*

/**
 * Compares two instances of [Chapter] by their fields in order: name,
 * parent legion, and marines count. Null values go first. The string
 * comparison is based on the [java.text.Collator] functionality and
 * depends on the given [locale].
 *
 * @property locale Locale to use for collation.
 */
class ChapterComparator(private val locale: Locale = Locale.ROOT) : Comparator<Chapter> {
    private val collator = Collator.getInstance(locale)
    override fun compare(left: Chapter, right: Chapter): Int {
        return Comparator
            .comparing(Chapter::name, collator)
            .thenComparing(Chapter::parentLegion, nullsFirst(collator))
            .thenComparing(Chapter::marinesCount)
            .compare(left, right)
    }
}