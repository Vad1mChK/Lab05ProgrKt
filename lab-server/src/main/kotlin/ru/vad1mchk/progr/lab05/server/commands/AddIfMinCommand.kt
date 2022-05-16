package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.connection.Request
import ru.vad1mchk.progr.lab05.common.connection.Response
import ru.vad1mchk.progr.lab05.common.exceptions.IdentifierCollisionException
import ru.vad1mchk.progr.lab05.common.messages.Message
import ru.vad1mchk.progr.lab05.server.util.Configuration

/**
 * A command with a [SpaceMarine] argument that does the following:
 *
 * Add this element to the collection if it is less than all other existing elements.
 */
class AddIfMinCommand: Command(
    "add_if_min",
    "{SPACE_MARINE}: Add this element if it is less than all others.",
    false
) {
    override fun invoke(request: Request): Any {
        try {
            Configuration.COLLECTION_MANAGER.collection().stream().anyMatch { it >= request.spaceMarine!! }.also {
                if (it) return Response(
                    Message(
                        Message.MessageType.INFO, Configuration.STRING_RESOURCES.getString("addIfMin failure")
                    )
                )
                Configuration.COLLECTION_MANAGER.add(request.spaceMarine!!)
                return Response(
                    Message(
                        Message.MessageType.INFO,Configuration.STRING_RESOURCES.getString("add success")
                    )
                )
            }
        } catch (e: IdentifierCollisionException) {
            return Response(Message(
                Message.MessageType.ERROR,
                e.message?:Configuration.STRING_RESOURCES.getString("IdentifierCollisionException")
            ))
        }
    }
}