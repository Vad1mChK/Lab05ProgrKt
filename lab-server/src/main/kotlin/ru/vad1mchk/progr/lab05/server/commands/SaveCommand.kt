package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.connection.Request
import ru.vad1mchk.progr.lab05.common.connection.Response
import ru.vad1mchk.progr.lab05.common.messages.Message
import ru.vad1mchk.progr.lab05.server.csv.CSVSerializer
import ru.vad1mchk.progr.lab05.server.util.Configuration
import java.io.IOException

/**
 * A command with no arguments that does the following:
 *
 * Save the collection into current file.
 */
class SaveCommand: Command (
    "save",
    "Save the collection into current file.",
    true
){
    override fun invoke(request: Request): Any {
        val serializer = CSVSerializer(Configuration.collectionFile)
        return Response(try {
            Message(Message.MessageType.INFO, Configuration.STRING_RESOURCES.getString("save success"))
        } catch (e: IOException) {
            Message(
                Message.MessageType.EXCEPTION,
                e.message ?: Configuration.STRING_RESOURCES.getString("IOException")
            )
        })
    }
}