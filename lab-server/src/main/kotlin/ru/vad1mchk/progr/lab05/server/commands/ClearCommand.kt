package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.connection.Request
import ru.vad1mchk.progr.lab05.common.connection.Response
import ru.vad1mchk.progr.lab05.common.messages.Message
import ru.vad1mchk.progr.lab05.server.util.Configuration

/**
 * A command with an [Int] argument and a [SpaceMarine] argument that does the following:
 *
 * Remove all elements from the collection.
 */
class ClearCommand: Command(
    "clear",
    "Remove all elements from the collection.",
    false
) {
    override fun invoke(request: Request): Any {
        Configuration.COLLECTION_MANAGER.clear()
        return Response(
            Message(Message.MessageType.INFO, Configuration.STRING_RESOURCES.getString("clear success"))
        )
    }
}