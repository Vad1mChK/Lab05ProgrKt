package ru.vad1mchk.progr.lab05.common.file

import ru.vad1mchk.progr.lab05.common.exceptions.FileCannotCreateException
import ru.vad1mchk.progr.lab05.common.exceptions.FileCannotOpenException
import ru.vad1mchk.progr.lab05.common.exceptions.FileException
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.InvalidPathException
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.exists

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

    fun setCheckReadable(flag: Boolean): FileManager {
        checkReadable = flag
        return this
    }

    fun setCheckWritable(flag: Boolean): FileManager {
        checkWritable = flag
        return this
    }

    fun setCheckExecutable(flag: Boolean): FileManager {
        checkExecutable = flag
        return this
    }

    fun create(): File {
        return try {
            Files.createFile(path).toFile()
        } catch (e: IOException) {
            throw FileCannotCreateException("Невозможно создать файл по заданному адресу: ошибка ввода-вывода.", e)
        } catch (e: FileAlreadyExistsException) {
            throw FileCannotCreateException("Невозможно создать файл по заданному адресу: файл уже существует.")
        }
    }

    fun open(): File {
        val file = path.toFile()
        if (file.exists()) {
            if (checkReadable && !file.canRead()) {
                throw FileCannotCreateException("Файл недоступен для чтения.")
            }
            if (checkWritable && !file.canWrite()) {
                throw FileCannotCreateException("Файл недоступен для записи.")
            }
            if (checkExecutable && !file.canExecute()) {
                throw FileCannotCreateException("Файл недоступен для исполнения.")
            }
            return file
        } else {
            throw FileCannotOpenException("Файл не найден или не существует.")
        }
    }
}