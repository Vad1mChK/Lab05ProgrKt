package ru.vad1mchk.progr.lab05.client.commands

/**
 * Interface to be implemented by command executor
 */
interface CommandExecutorInterface {
    /**
     * Executes the following command read by the CommandReader
     * if it is not null
     * @param command command to execute
     */
    fun executeCommand(command: Command?)

    /**
     * Outputs the help string when needed
     */
    fun help()
}