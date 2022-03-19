package ru.vad1mchk.progr.lab05.client.util

import java.io.File

object Serializer {
    lateinit var file: File

    fun joinPrimary(array: ArrayList<String?>): String {
        return array.map { (it?:"").replace(",", "\",\"") }.joinToString(",")
    }

    private fun joinSecondary(array: ArrayList<String?>): String {
        return array.map { (it?:"").replace(":", "\\:") }.joinToString(":")
    }
}