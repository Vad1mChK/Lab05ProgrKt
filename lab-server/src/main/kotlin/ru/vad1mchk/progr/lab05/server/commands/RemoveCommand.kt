package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.connection.Request
import ru.vad1mchk.progr.lab05.common.connection.Response
import ru.vad1mchk.progr.lab05.common.exceptions.IdentifierNotExistsException
import ru.vad1mchk.progr.lab05.common.messages.Message
import ru.vad1mchk.progr.lab05.server.util.Configuration

/**
 * A command with an [Int] argument that does the following:
 *
 * Remove the element with this ID from the collection.
 */
class RemoveCommand: Command(
    "remove_by_id",
    "ID: Remove the element with this ID from the collection.",
    false
) {
    override fun invoke(request: Request): Any {
        return Response(try {
            Configuration.COLLECTION_MANAGER.removeById(request.idArgument!!)
            Message(Message.MessageType.INFO, Configuration.STRING_RESOURCES.getString("remove success"))
        } catch (e: IdentifierNotExistsException) {
            Message(
                Message.MessageType.ERROR,
                e.message?: Configuration.STRING_RESOURCES.getString("IdentifierNotExistsException remove")
            )
        } catch (e: NullPointerException) {
            Message(
                Message.MessageType.ERROR,
                e.message ?: Configuration.STRING_RESOURCES.getString("exception missingCommandArgument")
            )
        })
    }
}