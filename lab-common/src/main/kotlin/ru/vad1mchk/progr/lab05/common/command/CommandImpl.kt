package ru.vad1mchk.progr.lab05.common.command

import ru.vad1mchk.progr.lab05.common.io.OutputManager
import ru.vad1mchk.progr.lab05.common.messages.Messages

/**
 * Basic implementation of [Command].
 * @property commandPermissions
 * @property description (Optional) command description printed out when calling help.
 */
abstract class CommandImpl(
    private val commandPermissions: CommandPermissions,
    private val description: String? = null
) : Command {

    override fun help(): String {
        return String.format(
                Messages.commandHelpFormat, "", description ?: Messages.commandDescriptionPlaceholder
        )
    }
}