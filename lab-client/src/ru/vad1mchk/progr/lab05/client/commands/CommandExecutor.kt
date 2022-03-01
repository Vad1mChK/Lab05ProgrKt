package ru.vad1mchk.progr.lab05.client.commands

import ru.vad1mchk.progr.lab05.client.collection.SpaceMarineCollectionHandler
import ru.vad1mchk.progr.lab05.client.exceptions.ProgramExitException
import ru.vad1mchk.progr.lab05.client.messages.Messages
import ru.vad1mchk.progr.lab05.client.util.CollectionSaver
import ru.vad1mchk.progr.lab05.client.commands.Command.CommandType.*
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
                HELP -> help()
                INFO -> println(Messages.get("badgeInfo")+handler.info())
                SHOW -> handler.show()
                ADD -> {
                    TODO("Not yet implemented")
                }
                UPDATE -> {
                    TODO("Not yet implemented")
                }
                REMOVE -> {
                    TODO("Not yet implemented")
                }
                CLEAR -> handler.clear()
                SAVE -> CollectionSaver.save(handler.collection, collectionFile)
                EXECUTE_SCRIPT -> {
                    TODO("Not yet implemented")
                }
                EXIT -> throw ProgramExitException()
                ADD_IF_MIN -> {
                    TODO("Not yet implemented")
                }
                REMOVE_GREATER -> {
                    TODO("Not yet implemented")
                }
                HISTORY -> historyStorage.print()
                FILTER_LESS_THAN_MELEE_WEAPON -> {
                    TODO("Not yet implemented")
                }
                FILTER_GREATER_THAN_HEART_COUNT -> {
                    TODO("Not yet implemented")
                }
                PRINT_FIELD_DESCENDING_HEALTH -> {
                    TODO("Not yet implemented")
                }
            }
            historyStorage.addCommand(command)
        }
    }

    override fun help() {
        println(Messages.get("helpString"))
    }
}