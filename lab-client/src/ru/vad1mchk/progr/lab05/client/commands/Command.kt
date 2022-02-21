package ru.vad1mchk.progr.lab05.client.commands


import ru.vad1mchk.progr.lab05.client.exceptions.EmptyCommandException
import ru.vad1mchk.progr.lab05.client.exceptions.IllegalCommandNameException
import java.util.*


/**
 * Class to work with commands passed to the input
 */
class Command(str: String) {
    enum class CommandType(val cmdName: String) {
        HELP("help"),
        INFO("info"),
        SHOW("show"),
        ADD("add"),
        UPDATE("update"),
        EXIT("exit");
        companion object {
            fun getByName(name: String): CommandType {
                for (value in values()) {
                    if (value.cmdName == name) {
                        return value
                    }
                }
                throw IllegalArgumentException()
            }
        }
    }
    /**
     * Name of command; always not empty
     */
    val commandType: CommandType

    /**
     * Argument of command; may be empty
     */
    var commandArg: String? = null

    /**
     * Primary constructor for the command from string passed to the input
     * @param str string representation
     */
    init {
        val arr = str.split("(\\s)(?=(?:[^\"]|\"[^\"]*\")*$)".toRegex()).toTypedArray()
        var len = arr.size
        if (len-- == 0) {
            throw EmptyCommandException("Empty line was entered. Please enter a valid command (type \"help\" to list available commands).")
        }
        try {
            commandType = CommandType.getByName(arr[0])
        } catch (e: IllegalArgumentException) {
            throw IllegalCommandNameException("No command with name ${arr[0]} exists.")
        }
        if (len > 0) {
            commandArg = arr[1]
        }
    }

    fun hasArg(): Boolean {
        return commandArg != null
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || javaClass != other.javaClass) {
            return false
        }
        val command = other as Command
        return commandType == command.commandType && commandArg == command.commandArg
    }

    override fun hashCode(): Int {
        return commandType.hashCode() + if (commandArg != null) commandArg.hashCode() else 0 xor Int.MAX_VALUE
    }

    override fun toString(): String {
        return "${commandType.cmdName} $commandArg"
    }
}