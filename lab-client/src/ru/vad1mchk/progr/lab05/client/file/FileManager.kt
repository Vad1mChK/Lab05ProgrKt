package ru.vad1mchk.progr.lab05.client.file

import ru.vad1mchk.progr.lab05.client.exceptions.FileAccessException
import ru.vad1mchk.progr.lab05.client.exceptions.FileCannotCreateException
import ru.vad1mchk.progr.lab05.client.exceptions.FileNotCSVException
import ru.vad1mchk.progr.lab05.client.exceptions.FileNotExistsException
import ru.vad1mchk.progr.lab05.client.messages.Messages
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path

object FileManager {
    fun create(path: Path) {
        try {
            Files.createFile(path)
        } catch (e: IOException) {
            throw FileCannotCreateException(Messages.errorFileCannotCreateIO)
        } catch (e: FileAlreadyExistsException) {
            throw FileCannotCreateException(Messages.errorFileCannotCreateAlreadyExists)
        }
    }

    fun assertIfCSV(path: Path) {
        if (path.toFile().extension.lowercase() != "csv")
            throw FileNotCSVException(Messages.errorFileNotCSV)
    }

    fun openFile(path: Path, checkIfCanRead: Boolean = true, checkIfCanWrite: Boolean = true): File {
        val file = path.toFile()
        if (path.toFile().exists()) {
            if ((file.canRead() || !checkIfCanRead) && (file.canWrite() || !checkIfCanWrite)) {
                return file
            }
            else if (!file.canRead() && (file.canWrite() || !checkIfCanWrite)){
                throw FileAccessException(Messages.errorFileAccessRead)
            }
            else if (!file.canWrite() && (file.canRead() || !checkIfCanRead)){
                throw FileAccessException(Messages.errorFileAccessWrite)
            }
            else {
                throw FileAccessException(Messages.errorFileAccessReadWrite)
            }
        } else {
            throw FileNotExistsException(Messages.exceptionFileNotExists)
        }
    }
}