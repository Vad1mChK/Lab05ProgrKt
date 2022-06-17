package ru.vad1mchk.progr.lab05.common.file

import ru.vad1mchk.progr.lab05.common.exceptions.FileCannotAccessException
import ru.vad1mchk.progr.lab05.common.exceptions.FileCannotCreateException
import ru.vad1mchk.progr.lab05.common.exceptions.FileCannotOpenException
import ru.vad1mchk.progr.lab05.common.exceptions.FileException
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.InvalidPathException
import java.nio.file.Path
import java.nio.file.Paths

/**
 * A builder class that opens or creates a file, checking its permissions if necessary.
 * @property filePath
 */
class FileManager(private val filePath: String) {
    private var checkReadable: Boolean = false
    private var checkWritable: Boolean = false
    private var checkExecutable: Boolean = false
    val path: Path

    init {
        try {
            path = Paths.get(filePath)
        } catch (e: InvalidPathException) {
            throw FileException("Невозможно найти указанный путь.")
        }
    }

    /**
     * Sets/resets the flag that determines whether read permissions should be checked before opening the file.
     * If set to `true` and the file is not readable, [FileCannotOpenException] is thrown when attempting to open.
     * @param flag New flag value.
     * @return This file manager.
     */
    fun setCheckReadable(flag: Boolean): FileManager {
        checkReadable = flag
        return this
    }

    /**
     * Sets/resets the flag that determines whether write permissions should be checked before opening the file.
     * If set to `true` and the file is not writable, [FileCannotOpenException] is thrown when attempting to open.
     * @param flag New flag value.
     * @return This file manager.
     */
    fun setCheckWritable(flag: Boolean): FileManager {
        checkWritable = flag
        return this
    }

    /**
     * Sets/resets the flag that determines whether execute permissions should be checked before opening the file.
     * If set to `true` and the file is not execute, [FileCannotOpenException] is thrown when attempting to open.
     * @param flag New flag value.
     * @return This file manager.
     */
    fun setCheckExecutable(flag: Boolean): FileManager {
        checkExecutable = flag
        return this
    }

    /**
     * Creates a new file by the file path [path].
     * @return The file created by this file manager.
     * @throws [FileCannotCreateException] if it was impossible to create the file.
     */
    @Throws(FileCannotCreateException::class)
    fun create(): File {
        return try {
            Files.createFile(path).toFile()
        } catch (e: IOException) {
            throw FileCannotCreateException("Невозможно создать файл по заданному адресу: ошибка ввода-вывода.", e)
        } catch (e: FileAlreadyExistsException) {
            throw FileCannotCreateException("Невозможно создать файл по заданному адресу: файл уже существует.")
        }
    }

    /**
     * Opens a file by the file path [path].
     * @return The file opened by this file manager.
     * @throws [FileCannotOpenException] if the file does not exist.
     * @throws [FileCannotAccessException] if the file does not have the necessary permissions.
     */
    @Throws(FileCannotOpenException::class, FileCannotAccessException::class)
    fun open(): File {
        val file = path.toFile()
        if (file.exists()) {
            if (checkReadable && !file.canRead()) {
                throw FileCannotAccessException("Файл недоступен для чтения.")
            }
            if (checkWritable && !file.canWrite()) {
                throw FileCannotAccessException("Файл недоступен для записи.")
            }
            if (checkExecutable && !file.canExecute()) {
                throw FileCannotAccessException("Файл недоступен для исполнения.")
            }
            return file
        } else {
            throw FileCannotOpenException("Файл не найден или не существует.")
        }
    }
}