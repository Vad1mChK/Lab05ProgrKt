package ru.vad1mchk.progr.lab05.client

import ru.vad1mchk.progr.lab05.client.collection.SpaceMarineCollectionHandler
import ru.vad1mchk.progr.lab05.client.commands.CommandExecutor
import ru.vad1mchk.progr.lab05.client.commands.CommandReader
import ru.vad1mchk.progr.lab05.client.commands.HistoryStorage
import ru.vad1mchk.progr.lab05.client.exceptions.*
import ru.vad1mchk.progr.lab05.client.messages.Messages
import ru.vad1mchk.progr.lab05.client.util.CollectionLoader
import ru.vad1mchk.progr.lab05.client.util.CollectionSaver
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream


fun main(args: Array<String>) {
    val collectionFile: File
    try {
        collectionFile = validateArgs(args)
    } catch (e: Exception) {
        if (e is ProgramArgumentException || e is AccessFileException) {
            println(Messages.get("badgeError") + e.message)
            return
        } else {
            throw e
        }
    }
    val collectionFileInputStream = FileInputStream(collectionFile)
    val collectionFileOutputStream = FileOutputStream(collectionFile)
    val collection = CollectionLoader.load(collectionFileInputStream)
    CollectionSaver.save(collection, collectionFileOutputStream)
    val commandReader = CommandReader(System.`in`)
    val commandExecutor = CommandExecutor(
        commandReader,
        SpaceMarineCollectionHandler(collection),
        collectionFileOutputStream,
        HistoryStorage()
    )
    while (true) {
        try {
            commandExecutor.executeCommand(commandReader.read())
        } catch (e: ProgramExitException) {
            break
        }
    }

}

fun validateArgs(args: Array<String>): File {
    if (args.isEmpty()) {
        throw EmptyProgramArgumentException(Messages.get("emptyProgramArgumentException"))
    }
    if (args.size > 1) {
        throw InvalidProgramArgumentException(Messages.get("invalidProgramArgumentExceptionMoreThanOne"))
    }
    val fileName = args[0]
    if (!StringBuffer(fileName).endsWith(".csv")) {
        throw InvalidProgramArgumentException(Messages.get("invalidProgramArgumentExceptionNotCSV"))
    }
    val collectionFile = File(fileName)
    val collectionFileInputStream: FileInputStream
    try {
        if (!collectionFile.canRead()) throw CannotReadFileException()
        collectionFileInputStream = FileInputStream(collectionFile)
    } catch (e: FileNotFoundException){
        throw AccessFileException(Messages.get("accessFileExceptionNotFound"), e)
    } catch (e: Exception) {
        throw Exception()
    }
    return collectionFile
}
