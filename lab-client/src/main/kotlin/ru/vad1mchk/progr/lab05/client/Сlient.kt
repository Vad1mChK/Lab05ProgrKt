package ru.vad1mchk.progr.lab05.client

import ru.vad1mchk.progr.lab05.client.csv.Deserializer
import ru.vad1mchk.progr.lab05.client.csv.Serializer
import ru.vad1mchk.progr.lab05.client.exceptions.EndProgramException
import ru.vad1mchk.progr.lab05.client.exceptions.FileException
import ru.vad1mchk.progr.lab05.client.file.FileManager
import ru.vad1mchk.progr.lab05.client.io.OutputManager
import ru.vad1mchk.progr.lab05.client.messages.Messages
import ru.vad1mchk.progr.lab05.client.terminal.Invoker
import java.io.File
import java.nio.file.FileSystems

fun main(args: Array<String>) {
    val file = try {
        pickFile(args)
    } catch (e: EndProgramException) {
        if (e.message != null) {
            OutputManager.sayError(e)
        }
        return
    }
    OutputManager.say(Messages.greetingString)
    Deserializer.file = file
    Serializer.file = file
    Deserializer.load()
    Invoker().terminalMode()
}

/**
 * Function that validates program arguments and determines the collection file, ending execution if it is impossible.
 * @param args Program arguments.
 * @return Collection file to open.
 * @throws EndProgramException if there are issues with loading the collection file.
 */
fun pickFile(args: Array<String>): File {
    if (args.size > 1) {
        OutputManager.sayError(Messages.errorTooManyProgramArguments)
        throw EndProgramException()
    } else if (args.size == 1) {
        try {
            return FileManager.openFile(FileSystems.getDefault().getPath(args[0]))
        } catch (e: FileException) {
            throw EndProgramException(e.message ?: "", e)
        }

    } else {
        OutputManager.sayWarning(Messages.warningNoProgramArguments)
        readLine().also { pathString ->
            if (pathString.isNullOrBlank()) {
                throw EndProgramException()
            }
            try {
                val path = FileSystems.getDefault().getPath(pathString)
                FileManager.create(path)
                return path.toFile()
            } catch (e: FileException) {
                throw EndProgramException(e.message ?: "", e)
            }
        }
    }
}