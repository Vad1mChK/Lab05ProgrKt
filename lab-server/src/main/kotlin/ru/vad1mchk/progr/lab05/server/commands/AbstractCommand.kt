package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.commands.Command
import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response

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

    fun getHelpString(): String {
        return """
        |$name ${argumentNames?:""}
        |${"\t"}$description
        """.trimMargin()
    }
}