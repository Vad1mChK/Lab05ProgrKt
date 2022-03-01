package ru.vad1mchk.progr.lab05.client.commands


import ru.vad1mchk.progr.lab05.client.datatypes.MeleeWeapon
import ru.vad1mchk.progr.lab05.client.exceptions.EmptyCommandException
import ru.vad1mchk.progr.lab05.client.exceptions.InvalidCommandArgumentException
import ru.vad1mchk.progr.lab05.client.exceptions.InvalidCommandNameException
import ru.vad1mchk.progr.lab05.client.messages.Messages
import java.util.*


/**
 * Class to work with commands passed to the input
 */
class Command(str: String) {
    enum class CommandType(val cmdName: String, val hasArg: Boolean = false, val hasDataInput: Boolean = false) {
        HELP("help"),
        INFO("info"),
        SHOW("show"),
        ADD("add", hasDataInput = true),
        UPDATE("update", hasArg = true, hasDataInput = true),
        REMOVE("remove", hasArg = true),
        CLEAR("clear"),
        SAVE("save"),
        EXECUTE_SCRIPT("execute_script", hasArg = true),
        EXIT("exit"),
        ADD_IF_MIN("add_if_min", hasDataInput = true),
        REMOVE_GREATER("remove_greater", hasDataInput = true),
        HISTORY("history"),
        FILTER_LESS_THAN_MELEE_WEAPON("filter_less_than_melee_weapon", hasArg = true),
        FILTER_GREATER_THAN_HEART_COUNT("filter_greater_than_heart_count", hasArg = true),
        PRINT_FIELD_DESCENDING_HEALTH("print_field_descending_health");


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
        val arr = str.trim().split("(\\s)(?=(?:[^\"]|\"[^\"]*\")*$)".toRegex()).toTypedArray()
        val len = arr.size
        if (arr.isEmpty() || arr[0] == "") {
            throw EmptyCommandException()
        }
        validate(arr)
        commandType = CommandType.getByName(arr[0])
        if (commandType.hasArg && arr.size == 2) {
            commandArg = arr[1]
        }
    }

    fun validate(arr: Array<String>) {
        var checkedCommandType: CommandType
        try {
            checkedCommandType = CommandType.getByName(arr[0])
        } catch (e: IllegalArgumentException) {
            throw InvalidCommandNameException(String.format(Messages.get("invalidCommandNameException"),arr[0]))
        }
        if (checkedCommandType.hasArg) {
            if (arr.size == 1){
                throw InvalidCommandArgumentException(String.format(Messages.get("invalidCommandArgumentExceptionMissingArg"),arr[0]))
            }
            if (arr.size > 2){
                throw InvalidCommandArgumentException(String.format(Messages.get("invalidCommandArgumentExceptionMoreThanOne"),arr[0]))
            }
            val arg = arr[1]
            when (checkedCommandType) {
                CommandType.UPDATE, CommandType.REMOVE -> {
                    try {
                        arg.toInt()
                    } catch (e: NumberFormatException) {
                        throw InvalidCommandArgumentException(String.format(Messages.get("invalidCommandArgumentExceptionIllegalDatatype"),arr[0],"Int"))
                    }
                }
                CommandType.FILTER_GREATER_THAN_HEART_COUNT -> {
                    try {
                        arg.toLong()
                    } catch (e: NumberFormatException) {
                        throw InvalidCommandArgumentException(String.format(Messages.get("invalidCommandArgumentExceptionIllegalDatatype"),arr[0],"Long"))
                    }
                }
                CommandType.FILTER_LESS_THAN_MELEE_WEAPON -> {
                    try {
                        MeleeWeapon.valueOf(arg)
                    } catch (e: IllegalArgumentException) {
                        throw InvalidCommandArgumentException(String.format(Messages.get("invalidCommandArgumentExceptionIllegalDatatype"),arr[0],"MeleeWeapon"))
                    }
                }
            }
        } else {
            if (arr.size >= 2){
                throw InvalidCommandArgumentException(String.format(Messages.get("invalidCommandArgumentExceptionUnwantedArgs"),arr[0]))
            }
        }
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