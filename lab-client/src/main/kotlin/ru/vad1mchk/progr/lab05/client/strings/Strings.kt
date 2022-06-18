package ru.vad1mchk.progr.lab05.client.strings

import java.util.ListResourceBundle

abstract class Strings: ListResourceBundle() {
    abstract val content: Array<Array<Any>>

    override fun getContents(): Array<Array<Any>> {
        return content
    }

    operator fun get(index: String): String? {
        return try {
            val pair = content.first { it.component1() == index }
            return pair.component2().toString()
        } catch (e: IndexOutOfBoundsException) {
            null
        } catch (e: NoSuchElementException) {
            null
        }
    }
}