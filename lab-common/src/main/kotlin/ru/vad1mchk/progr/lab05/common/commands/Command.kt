package ru.vad1mchk.progr.lab05.common.commands

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response

/** Base interface for all commands. */
interface Command {
    /**
     * Executes this command on this request if it is possible and
     * returns a response.
     *
     * @param request Request to execute.
     * @return The execution result, if any.
     */
    operator fun invoke(request: Request): Response?
}