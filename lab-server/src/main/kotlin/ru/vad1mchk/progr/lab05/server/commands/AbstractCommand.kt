package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.commands.Command
import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response

/**
 * Base class for all invokable commands.
 * @property name The command name.
 * @property description The command description.
 * @property argumentNames The names of the command arguments (optional).
 * @property isServerOnly If this command can only be used by the server.
 */
abstract class AbstractCommand(
    val name: String,
    val description: String,
    val argumentNames: String? = null,
    val isServerOnly: Boolean = false
): Command {
    companion object {
        val commandsList = ArrayList<AbstractCommand>()
    }

    init {
        commandsList.add(this)
    }

    /**
     * Gets help for this command, containing name, arguments and description.
     * @return The help string for this command.
     */
    fun getHelpString(): String {
        return """
        |$name ${argumentNames?:""}
        |${"\t"}$description
        """.trimMargin()
    }
}