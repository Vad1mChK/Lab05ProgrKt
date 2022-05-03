package ru.vad1mchk.progr.lab05.common.file

import ru.vad1mchk.progr.lab05.common.exceptions.FileAccessException
import ru.vad1mchk.progr.lab05.common.exceptions.FileCannotCreateException
import ru.vad1mchk.progr.lab05.common.exceptions.FileNotCSVException
import ru.vad1mchk.progr.lab05.common.exceptions.FileNotExistsException
import ru.vad1mchk.progr.lab05.common.messages.StringResources
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path

/**
 * Class that either opens an existing file or creates a new one.
 */
class FileManager(private val path: Path) {
    companion object {
        val stringResources = StringResources()
    }

    /**
     * Attempts to create a file by this [path].
     */
    fun create() {
        try {
            Files.createFile(path)
        } catch (e: IOException) {
            throw FileCannotCreateException(stringResources.getString("FileCannotCreateException IO"), e)
        } catch (e: FileAlreadyExistsException) {
            throw FileCannotCreateException(stringResources.getString("FileCannotCreateException alreadyExists"), e)
        }
    }

    /**
     * Checks if the collection file that you try to open has the CSV extension.
     */
    fun assertIfCSV() {
        if (path.toFile().extension.lowercase() != "csv") {
            throw FileNotCSVException(stringResources.getString("FileNotCSVException"))
        }
    }

    /**
     * Opens the file if it exists and has read and/or write permissions. In some cases, it is not necessary for the
     * file to have one of these permissions, so you can turn the check off.
     * @param checkIfCanRead If you should check if the file has read permissions.
     * @param checkIfCanWrite If you should check if the file has write permissions.
     * @return The file if it was opened successfully.
     */
    fun openFile(checkIfCanRead: Boolean = true, checkIfCanWrite: Boolean = true): File {
        val file = path.toFile()
        if (path.toFile().exists()) {
            if ((file.canRead() || !checkIfCanRead) && (file.canWrite() || !checkIfCanWrite)) {
                return file
            } else if (!file.canRead() && (file.canWrite() || !checkIfCanWrite)) {
                throw FileAccessException(stringResources.getString("FileAccessException read"))
            } else if (!file.canWrite() && (file.canRead() || !checkIfCanRead)) {
                throw FileAccessException(stringResources.getString("FileAccessException write"))
            } else {
                throw FileAccessException(stringResources.getString("FileAccessException read write"))
            }
        } else {
            throw FileNotExistsException(stringResources.getString("FileNotExistsException"))
        }
    }
}