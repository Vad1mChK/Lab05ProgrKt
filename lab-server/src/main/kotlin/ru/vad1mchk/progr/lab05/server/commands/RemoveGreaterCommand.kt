package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.connection.Request
import ru.vad1mchk.progr.lab05.common.connection.Response
import ru.vad1mchk.progr.lab05.common.exceptions.IdentifierNotExistsException
import ru.vad1mchk.progr.lab05.common.messages.Message
import ru.vad1mchk.progr.lab05.server.util.Configuration

/**
 * A command with a [SpaceMarine] argument that does the following:
 *
 * Remove all elements greater than this element from the collection.
 */
class RemoveGreaterCommand: Command (
        "remove_greater",
    "{SPACE_MARINE}: Remove all elements greater than this element from the collection.",
    false
) {
    override fun invoke(request: Request): Any {
        try {
            val idsToRemove = Configuration.COLLECTION_MANAGER.collection()
                .filter { it > request.spaceMarine!! }
                .map { it.id }
            if (idsToRemove.isEmpty()) {
                return Response(
                    Message(
                        Message.MessageType.INFO,
                        Configuration.STRING_RESOURCES.getString("removeGreater failure")
                    )
                )
            }
            idsToRemove.forEach(Configuration.COLLECTION_MANAGER::removeById)
            return Response(
                Message(
                    Message.MessageType.INFO,
                    Configuration.STRING_RESOURCES.getString("removeGreater success")
                )
            )
        } catch (e: IdentifierNotExistsException) {
            return Response(
                Message(
                    Message.MessageType.ERROR,
                e.message?: Configuration.STRING_RESOURCES.getString("IdentifierNotExistsException remove")
                )
            )
        }
    }
}