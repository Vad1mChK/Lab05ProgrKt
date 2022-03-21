package ru.vad1mchk.progr.lab05.client.command

/**
 * Base interface for all commands.
 */
@FunctionalInterface
interface Command {
    /**
     * Command invocation method.
     */
    operator fun invoke(arg: String? = null)
}