package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.connection.Request
import ru.vad1mchk.progr.lab05.common.connection.Response
import ru.vad1mchk.progr.lab05.common.messages.Message
import ru.vad1mchk.progr.lab05.server.util.Configuration

/**
 * Easter egg, I guess?
 */
class SomeCommandWithHilariouslyLongNameThatDoesSomeFunnyStuffWithSpaceMarinesCommand: Command(
    "some_command_with_hilariously_long_name_that_does_some_funny_stuff_with_space_marines",
    "This command's name is self-explanatory.",
    false
) {
    override fun invoke(request: Request): Any {
        return Response(
            Message(Message.MessageType.INFO, "Okay, here is a random space marine for you:\n"
                    +Configuration.COLLECTION_MANAGER.collection().random().toCoolerString(Configuration.currentLocale)
            )
        )
    }
}