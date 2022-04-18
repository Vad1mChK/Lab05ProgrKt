package ru.vad1mchk.progr.lab05.common.io

import ru.vad1mchk.progr.lab05.common.file.FileManager
import java.nio.file.Path
import java.util.*

class ScriptInputManager(path: Path) : InputManagerImpl(
    Scanner(
        FileManager.openFile(path)
    )
) {
    init {
        scanner.useDelimiter("\n")
    }
}