package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.connection.Request

/**
 * Base class for all commands that can be executed.
 * @property commandName Name of the command.
 * @property commandDescription Description of the command: arguments order and what the command does.
 * @property isOnlyServerCommand Set to `true` if this command can only be executed from the server side, else `false`
 */
abstract class Command(
    val commandName: String,
    val commandDescription: String,
    val isOnlyServerCommand: Boolean
) {
    companion object {
        /**
         * List of commands that the client can call.
         */
        val CLIENT_COMMANDS_LIST = ArrayList<Command>()
    }

    init {
        if(!isOnlyServerCommand) {
            CLIENT_COMMANDS_LIST.add(this)
        }
    }

    /**
     * Executes this command according to the specified [request] and returns something.
     * @param request Request to perform.
     * @return Result of command execution, if any.
     */
    abstract operator fun invoke(request: Request): Any?

    override fun toString(): String {
        return "$commandName\n\t$commandDescription"
    }
}