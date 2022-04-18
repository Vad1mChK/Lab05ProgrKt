package ru.vad1mchk.progr.lab05.common.command

/**
 * Base interface for all commands.
 */

interface Command {
    /**
     * Invokes the command without arguments.
     */
    operator fun invoke()

    /**
     * Invokes the command with some arguments.
     * @param args Optional arguments for the command, no less than one.
     */
    operator fun invoke(args: Array<String>)
}