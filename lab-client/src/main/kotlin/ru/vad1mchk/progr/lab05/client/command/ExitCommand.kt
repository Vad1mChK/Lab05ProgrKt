package ru.vad1mchk.progr.lab05.client.command

import ru.vad1mchk.progr.lab05.common.command.CommandImpl
import ru.vad1mchk.progr.lab05.common.command.CommandPermissions
import ru.vad1mchk.progr.lab05.common.exceptions.EndProgramException
import ru.vad1mchk.progr.lab05.common.exceptions.InvalidCommandArgumentException
import ru.vad1mchk.progr.lab05.common.messages.Messages

/**
 * Exits the program (without saving).
 */
class ExitCommand : CommandImpl(
    CommandPermissions.ALL,
    String.format(
        Messages.commandHelpFormat,
        "",
        Messages.commandDescriptionExit
    )
) {
    override fun invoke() {
        throw EndProgramException()
    }

    override fun invoke(args: Array<String>) {
        throw InvalidCommandArgumentException(Messages.exceptionRedundantCommandArgument)
    }
}