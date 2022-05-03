package ru.vad1mchk.progr.lab05.common.io

/**
 * A class to store the name and arguments of a command temporarily.
 * @property commandName Name of the command.
 * @property commandArguments (Optional) arguments of the command.
 */
data class CommandWrapper(val commandName: String, val commandArguments: Array<out String>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CommandWrapper) return false

        if (commandName != other.commandName) return false
        if (!commandArguments.contentEquals(other.commandArguments)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = commandName.hashCode()
        result = 31 * result + commandArguments.contentHashCode()
        return result
    }
}