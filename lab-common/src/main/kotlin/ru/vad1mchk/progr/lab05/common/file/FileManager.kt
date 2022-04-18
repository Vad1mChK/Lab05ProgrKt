package ru.vad1mchk.progr.lab05.common.file

import ru.vad1mchk.progr.lab05.common.exceptions.FileAccessException
import ru.vad1mchk.progr.lab05.common.exceptions.FileCannotCreateException
import ru.vad1mchk.progr.lab05.common.exceptions.FileNotCSVException
import ru.vad1mchk.progr.lab05.common.exceptions.FileNotExistsException
import ru.vad1mchk.progr.lab05.common.messages.Messages
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path

/**
 * Object that either opens an existing file or creates a new one.
 */
object FileManager {
    /**
     * Attempts to create a file by the specified path.
     * @param path Path of the file.
     */
    fun create(path: Path) {
        try {
            Files.createFile(path)
        } catch (e: IOException) {
            throw FileCannotCreateException(Messages.errorFileCannotCreateIO)
        } catch (e: FileAlreadyExistsException) {
            throw FileCannotCreateException(Messages.errorFileCannotCreateAlreadyExists)
        }
    }

    /**
     * Checks if the collection file that you try to open has the CSV extension.
     * @param path Path of the collection file.
     */
    fun assertIfCSV(path: Path) {
        if (path.toFile().extension.lowercase() != "csv")
            throw FileNotCSVException(Messages.errorFileNotCSV)
    }

    /**
     * Opens the file if it exists and has read and/or write permissions. In some cases, it is not necessary for the
     * file to have one of these permissions, so you can turn the check off.
     * @param path Path of the collection file.
     * @param checkIfCanRead If you should check if the file has read permissions.
     * @param checkIfCanWrite If you should check if the file has write permissions.
     * @return The file if it was opened successfully.
     */
    fun openFile(path: Path, checkIfCanRead: Boolean = true, checkIfCanWrite: Boolean = true): File {
        val file = path.toFile()
        if (path.toFile().exists()) {
            if ((file.canRead() || !checkIfCanRead) && (file.canWrite() || !checkIfCanWrite)) {
                return file
            } else if (!file.canRead() && (file.canWrite() || !checkIfCanWrite)) {
                throw FileAccessException(Messages.errorFileAccessRead)
            } else if (!file.canWrite() && (file.canRead() || !checkIfCanRead)) {
                throw FileAccessException(Messages.errorFileAccessWrite)
            } else {
                throw FileAccessException(Messages.errorFileAccessReadWrite)
            }
        } else {
            throw FileNotExistsException(Messages.exceptionFileNotExists)
        }
    }
}