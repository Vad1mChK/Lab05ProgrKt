package ru.vad1mchk.progr.lab05.client.command

import ru.vad1mchk.progr.lab05.client.ClientApplication
import ru.vad1mchk.progr.lab05.common.command.CommandImpl
import ru.vad1mchk.progr.lab05.common.command.CommandPermissions
import ru.vad1mchk.progr.lab05.common.command.Commander
import ru.vad1mchk.progr.lab05.common.connection.CommandMessage
import ru.vad1mchk.progr.lab05.common.connection.ResponseMessage
import ru.vad1mchk.progr.lab05.common.exceptions.InvalidCommandArgumentException
import ru.vad1mchk.progr.lab05.common.messages.Messages
import java.nio.file.Path
import java.util.Stack

/**
 * Client implementation of the commander.
 * @constructor Primary constructor for the commander class.
 * @param clientApplication A single client application used by the program.
 */
class ClientCommander(clientApplication: ClientApplication): Commander() {
    var clientApplication: ClientApplication
    private set
    init {
        this.clientApplication = clientApplication
        register("help", object: CommandImpl(CommandPermissions.ALL, Messages.commandDescriptionHelp) {
            override fun invoke() {
                printHelp()
            }

            override fun invoke(args: Array<String>) {
                throw InvalidCommandArgumentException(Messages.exceptionRedundantCommandArgument)
            }
        } )
        register("exit", ExitCommand())
        register("execute_script", ExecuteScriptCommand(this))
    }

    override fun runCommand(commandMessage: CommandMessage): ResponseMessage {
        TODO("Not yet implemented")
    }

}