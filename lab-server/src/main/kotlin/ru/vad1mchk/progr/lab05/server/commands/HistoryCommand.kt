package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.connection.Request

/**
 * A command without arguments that does the following:
 *
 * Prints out several last commands executed.
 */
class HistoryCommand: Command(
    "history",
    "Prints out several last commands executed.",
    false
) {
    override fun invoke(request: Request): Any? {
        TODO("Not yet implemented")
    }
}