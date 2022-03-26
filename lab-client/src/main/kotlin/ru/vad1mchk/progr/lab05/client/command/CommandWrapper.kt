package ru.vad1mchk.progr.lab05.client.command

/**
 * Command wrapper class that stores the name and the
 * argument of the command.
 *
 * @property commandName Name of the command.
 * @property argument (Optional) argument of the
 * command.
 */
class CommandWrapper(
    val commandName: String,
    val argument: String? = null
)