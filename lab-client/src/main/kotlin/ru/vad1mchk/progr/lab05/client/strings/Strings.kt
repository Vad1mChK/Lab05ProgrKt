package ru.vad1mchk.progr.lab05.client.strings

import java.util.*

abstract class Strings : ListResourceBundle() {
    abstract val content: Array<Array<String>>

    override fun getContents(): Array<Array<String>> {
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