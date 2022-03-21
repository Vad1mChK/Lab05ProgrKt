package ru.vad1mchk.progr.lab05.client.file

import java.nio.file.Path
import java.util.*

/**
 * Manages input from files.
 * @param path Path to script file.
 */
class FileInputManager(path: Path) : AbstractInputManager(
    Scanner(
        FileManager.openFile(path)
    )
) {
    init {
        scanner.useDelimiter("\n")
    }
}