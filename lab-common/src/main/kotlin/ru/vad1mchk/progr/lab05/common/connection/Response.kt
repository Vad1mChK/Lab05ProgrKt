package ru.vad1mchk.progr.lab05.common.connection

import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.common.messages.Message
import java.util.LinkedList
import java.io.Serializable

data class Response(val message: Message, val spaceMarineCollection: LinkedList<SpaceMarine>? = null): Serializable {
    companion object {
        private val serialVersionUID = 42069672L
    }
}