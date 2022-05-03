package ru.vad1mchk.progr.lab05.server

import ru.vad1mchk.progr.lab05.common.datatypes.Coordinates
import ru.vad1mchk.progr.lab05.common.datatypes.MeleeWeapon
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.common.io.OutputManager
import ru.vad1mchk.progr.lab05.common.io.TerminalInputManager
import ru.vad1mchk.progr.lab05.server.application.ServerApplication
import ru.vad1mchk.progr.lab05.server.util.Configuration
import java.time.LocalDate
import java.util.*

/**
 * Main entry point of the program from the server side.
 */
object Server {
    /**
     * Main entry point of the program from the server side. You must specify the path to collection file.
     * @param args Arguments of the program.
     * */
    @JvmStatic
    fun main(args: Array<String>) {
        val inputManager = TerminalInputManager(locale = Locale.ROOT, isServer = true)
        while (true) {
            inputManager.readCommand().also {
                println("name: "+it.commandName)
                println("args: "+it.commandArguments.toList())
            }
        }
    }
}