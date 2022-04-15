package ru.vad1mchk.progr.lab05.common.commands

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