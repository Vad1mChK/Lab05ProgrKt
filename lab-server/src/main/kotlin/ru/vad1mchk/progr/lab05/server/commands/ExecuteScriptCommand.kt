package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.connection.Request

/**
 * A command with a path argument that does the following:
 *
 * SExecute the script in the current file.
 */
class ExecuteScriptCommand: Command(
    "execute_script",
    "FILE_NAME: Executes a script from file located by FILE_NAME",
    false
) {
    override fun invoke(request: Request): Any? {
        return null
    }
}