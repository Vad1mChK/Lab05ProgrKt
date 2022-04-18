package ru.vad1mchk.progr.lab05.client.command

import ru.vad1mchk.progr.lab05.common.command.CommandImpl
import ru.vad1mchk.progr.lab05.common.command.CommandPermissions
import ru.vad1mchk.progr.lab05.common.command.Commander.Companion.pathStack
import ru.vad1mchk.progr.lab05.common.exceptions.FileAccessException
import ru.vad1mchk.progr.lab05.common.exceptions.InvalidCommandArgumentException
import ru.vad1mchk.progr.lab05.common.exceptions.RecursiveScriptCallException
import ru.vad1mchk.progr.lab05.common.file.FileManager
import ru.vad1mchk.progr.lab05.common.io.OutputManager
import ru.vad1mchk.progr.lab05.common.messages.Messages
import java.nio.file.FileSystems


class ExecuteScriptCommand(clientCommander: ClientCommander) : CommandImpl(
    CommandPermissions.ALL,
    Messages.commandDescriptionExecuteScript
) {
    val clientCommander: ClientCommander
    init {
        this.clientCommander = clientCommander
    }
    override fun invoke() {
        throw InvalidCommandArgumentException(Messages.exceptionMissingCommandArgument)
    }

    override fun invoke(args: Array<String>) {
        if (args.size != 1) {
            throw InvalidCommandArgumentException(Messages.exceptionRedundantCommandArgument)
        }
        val arg = args[0]
        val path = try {
            FileSystems.getDefault().getPath(arg).also {
                FileManager.openFile(it, true, false)
            }
        } catch (e: FileAccessException) {
            throw InvalidCommandArgumentException(Messages.exceptionInvalidCommandArgumentPathNotAccessible)
        }
        if (path in pathStack) {
            throw RecursiveScriptCallException(Messages.exceptionRecursiveScriptCall)
        }
        pathStack.push(path)
        val process = ClientCommander(clientCommander.clientApplication)
        process.scriptMode(path)
        pathStack.pop()
        OutputManager.sayInfo(Messages.scriptExecutionEnd, path.fileName)
    }
}