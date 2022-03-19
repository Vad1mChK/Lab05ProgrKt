package ru.vad1mchk.progr.lab05.client

import ru.vad1mchk.progr.lab05.client.commands.*
import ru.vad1mchk.progr.lab05.client.datatypes.Coordinates
import ru.vad1mchk.progr.lab05.client.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.client.exceptions.ExitProgramException
import ru.vad1mchk.progr.lab05.client.manager.SpaceMarineCollectionManager
import ru.vad1mchk.progr.lab05.client.messages.Messages
import ru.vad1mchk.progr.lab05.client.terminal.Invoker
import ru.vad1mchk.progr.lab05.client.util.Deserializer
import ru.vad1mchk.progr.lab05.client.util.Serializer
import ru.vad1mchk.progr.lab05.client.util.UserInputReader
import java.nio.file.FileSystems
import java.time.LocalDate

fun main(args: Array<String>) {
    try {
        validateArgs(args)
    } catch (e: ExitProgramException) {
        if (e.message != null) {
            Messages.say(e.message, level = Messages.Level.FATAL_ERROR)
        }
        return
    }
    val path = FileSystems.getDefault().getPath("lab-client", "collection.csv")
    Deserializer.file = path.toFile()
    Deserializer.readFile()
    Invoker.execute("show")
    println(UserInputReader.readCoordinates())
}

fun validateArgs(args: Array<String>) {
    // throw ExitProgramException("varargparse")
}