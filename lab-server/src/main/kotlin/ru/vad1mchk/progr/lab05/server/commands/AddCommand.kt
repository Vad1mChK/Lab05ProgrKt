package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.connection.Request
import ru.vad1mchk.progr.lab05.common.connection.Response
import ru.vad1mchk.progr.lab05.common.exceptions.IdentifierCollisionException
import ru.vad1mchk.progr.lab05.common.messages.Message
import ru.vad1mchk.progr.lab05.server.util.Configuration

/**
 * A command with a [SpaceMarine] argument that does the following:
 *
 * Add this element to the collection.
 */
class AddCommand: Command(
    "add",
    "{SPACE_MARINE}: Add this element to the collection.",
    false
) {
    override fun invoke(request: Request): Any {
        return Response(try {
            Configuration.COLLECTION_MANAGER.add(request.spaceMarine!!)
            Message(Message.MessageType.INFO, Configuration.STRING_RESOURCES.getString("add success"))
        } catch (e: IdentifierCollisionException) {
            Message(
                Message.MessageType.ERROR,
                e.message?:Configuration.STRING_RESOURCES.getString("IdentifierCollisionException")
            )
        })
    }
}