package ru.vad1mchk.progr.lab05.client.util

import ru.vad1mchk.progr.lab05.client.exceptions.InvalidDataException
import ru.vad1mchk.progr.lab05.client.messages.Messages

object BooleanParser {
    fun parse(string: String): Boolean {
        if (string == "true") return true
        if (string == "false") return false
        throw InvalidDataException(Messages.exceptionDataInvalidBoolean)
    }
}