package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.connection.Request
import ru.vad1mchk.progr.lab05.common.connection.Response
import ru.vad1mchk.progr.lab05.common.messages.Message

/**
 * A command with no arguments that does the following:
 *
 * Print help for all available commands.
 */
class HelpCommand: Command(
    "help",
    "Print help for all available commands.",
    false
) {
    override fun invoke(request: Request): Any {
        return Response(Message(CLIENT_COMMANDS_LIST.joinToString("\n") { it.commandDescription }))
    }
}