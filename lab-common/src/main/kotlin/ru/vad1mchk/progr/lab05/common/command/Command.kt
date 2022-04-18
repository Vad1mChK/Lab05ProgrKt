package ru.vad1mchk.progr.lab05.common.command

import ru.vad1mchk.progr.lab05.common.exceptions.InvalidCommandArgumentException

/**
 * Base interface for all commands.
 */
interface Command {
    /**
     * Command invocation method without arguments.
     * @throws InvalidCommandArgumentException if the command cannot be invoked without arguments.
     */
    @Throws(InvalidCommandArgumentException::class)
    operator fun invoke()

    /**
     * Command invocation method with at least one string argument.
     * @param args String arguments of the command.
     * @throws InvalidCommandArgumentException if the command cannot be executed with these arguments.
     */
    @Throws(InvalidCommandArgumentException::class)
    operator fun invoke(args: Array<String>)

    /**
     * Returns help for this command only.
     */
    fun help(): String
}