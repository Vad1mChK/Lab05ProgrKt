package ru.vad1mchk.progr.lab05.client.strings

import java.util.*

abstract class Strings : ListResourceBundle() {
    companion object {
        const val VERSION = "2.71.828.1828"
        const val LABORATORY_WORK_NUMBER = 8
        const val PROGRAMMING_LANGUAGE = "Kotlin"
        val PROGRAMMING_LANGUAGE_VERSION = KotlinVersion.CURRENT.toString()
        const val JAVAFX_VERSION = 8
        const val JDK_VERSION = "1.8.0"

    }

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