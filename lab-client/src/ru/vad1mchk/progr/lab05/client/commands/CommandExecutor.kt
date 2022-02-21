package ru.vad1mchk.progr.lab05.client.commands

import ru.vad1mchk.progr.lab05.client.collection.SpaceMarineCollectionHandler
import ru.vad1mchk.progr.lab05.client.exceptions.ProgramExitException
import ru.vad1mchk.progr.lab05.client.messages.Messages
import java.io.FileOutputStream

/**
 * Class that is responsible for command execution. It can call
 * the methods of other classes when necessary
 */
class CommandExecutor(var reader: CommandReader,
                      private val handler: SpaceMarineCollectionHandler,
                      val collectionFile: FileOutputStream,
                      private val historyStorage: HistoryStorage): CommandExecutorInterface {
    override fun executeCommand(command: Command?) {
        if (command!= null) {
            when (command.commandType) {
                Command.CommandType.HELP -> {
                    help()
                }
                Command.CommandType.EXIT -> {
                    throw ProgramExitException()
                }
                Command.CommandType.INFO -> {
                    println(Messages.get("badgeInfo")+handler.info())
                }
                Command.CommandType.HISTORY -> {
                    historyStorage.print()
                }
                else -> {}
            }
            historyStorage.addCommand(command)
        }
    }

    override fun help() {
        println(Messages.get("helpString"))
    }
}