package ru.vad1mchk.progr.lab05.common.io

import ru.vad1mchk.progr.lab05.common.file.FileManager
import java.nio.file.Path
import java.util.*

class ScriptInputManager(path: Path, locale: Locale) : AbstractInputManager(
    Scanner(FileManager(path).openFile(checkIfCanWrite = false)), locale
) {
    init {
        scanner.useDelimiter("\n")
    }
}