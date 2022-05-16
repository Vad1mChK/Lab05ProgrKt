package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.connection.Request
import ru.vad1mchk.progr.lab05.common.connection.Response
import ru.vad1mchk.progr.lab05.common.exceptions.IdentifierNotExistsException
import ru.vad1mchk.progr.lab05.common.messages.Message
import ru.vad1mchk.progr.lab05.server.util.Configuration

/**
 * A command with an [Int] argument and a [SpaceMarine] argument that does the following:
 *
 * Update this ID in the collection with a new element.
 */
class UpdateCommand: Command(
    "update_id",
    "ID {SPACE_MARINE}: Update this ID in the collection with a new element.",
    false
) {
    override fun invoke(request: Request): Any {
        return Response(try {
            Configuration.COLLECTION_MANAGER.updateById(request.idArgument!!, request.spaceMarine!!)
            Message(Message.MessageType.INFO, Configuration.STRING_RESOURCES.getString("update success"))
        } catch (e: IdentifierNotExistsException) {
            Message(
                Message.MessageType.ERROR,
                e.message?: Configuration.STRING_RESOURCES.getString("IdentifierNotExistsException update")
            )
        } catch (e: NullPointerException) {
            Message(
                Message.MessageType.ERROR,
                e.message ?: Configuration.STRING_RESOURCES.getString("exception missingCommandArgument")
            )
        })
    }
}