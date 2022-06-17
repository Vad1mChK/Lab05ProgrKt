package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.commands.Command
import ru.vad1mchk.progr.lab05.common.communication.Request
import java.util.*

/**
 * Base class for all invokable commands.
 * @property name The command name.
 * @property description The command description.
 * @property argumentNames The names of the command arguments (optional).
 * @property isServerOnly If this command can only be used by the server.
 * @property isLoggedInOnly If this command can only be used by the logged-in user or server.
 */
abstract class AbstractCommand(
    val name: String,
    val description: String,
    val argumentNames: String? = null,
    val permissions: Int
) : Command, Comparable<AbstractCommand> {
    protected companion object {
        val commandsList = TreeSet<AbstractCommand>()
        const val FOR_ALL = 0b111
        const val FOR_SERVER_AND_LOGGED_IN_CLIENT = 0b110
        const val FOR_SERVER = 0b100
        protected const val FOR_LOGGED_IN_CLIENT = 0b010
        const val FOR_LOGGED_OUT_CLIENT = 0b001
    }

    init {
        commandsList += this
    }

    /**
     * Gets help for this command, containing name, arguments and description.
     * @return The help string for this command.
     */
    fun getHelpString(): String {
        return """
        |$name ${argumentNames ?: ""}
        |${"\t"}$description
        """.trimMargin()
    }

    fun canBeInvokedBy(request: Request): Boolean {
        val accessLevel = if (request.isServerRequest) 0b100 else if (request.isLoggedInRequest) 0b010 else 0b001
        return (accessLevel and permissions) != 0
    }

    override fun compareTo(other: AbstractCommand): Int {
        return compareValuesBy(
            this,
            other,
            AbstractCommand::name,
            AbstractCommand::description,
            AbstractCommand::argumentNames,
            AbstractCommand::permissions
        )
    }
}