package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.connection.Request
import ru.vad1mchk.progr.lab05.common.connection.Response
import ru.vad1mchk.progr.lab05.common.messages.Message
import ru.vad1mchk.progr.lab05.server.util.Configuration

/**
 * A command with no arguments that does the following:
 *
 * Print out information about the collection.
 */
class InfoCommand: Command(
    "info",
    "Print out information about the collection.",
    false
) {
    override fun invoke(request: Request): Any {
        return Response(Message(Message.MessageType.INFO, Configuration.COLLECTION_MANAGER.info()))
    }
}