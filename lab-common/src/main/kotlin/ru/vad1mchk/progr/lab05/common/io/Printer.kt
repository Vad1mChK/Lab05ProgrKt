package ru.vad1mchk.progr.lab05.common.io

import java.io.PrintWriter

/**
 * Object that manages terminal output.
 */
object Printer {
    private const val ESCAPE_CHAR = 27.toChar()
    private const val ERROR_BADGE = "[$ESCAPE_CHAR[31;1mОШИБКА$ESCAPE_CHAR[0m] "

    /**
     * Prints this message without a new line, formatted with optional arguments, much like [PrintWriter.printf].
     * @param message Message to print (a format string).
     * @param args Arguments to format the string with.
     */
    @JvmStatic
    fun printNoNewLine(message: String, vararg args: Any?) {
        print(message.format(*args))
    }

    /**
     * Prints this message adding a new line, formatted with optional arguments, much like [PrintWriter.printf].
     * @param message Message to print (a format string).
     * @param args Arguments to format the string with.
     */
    @JvmStatic
    fun printNewLine(message: String, vararg args: Any?) {
        println(message.format(*args))
    }

    /**
     * Prints a message with an error message with error badge prepended to it.
     * @param message Message to print.
     */
    @JvmStatic
    fun printError(message: String) {
        println(ERROR_BADGE+message)
    }

    /**
     * Prints the message of this throwable with error badge prepended to it.
     * @param e Throwable to get the message from.
     */
    @JvmStatic
    fun printError(e: Throwable) {
        println(ERROR_BADGE+(e.message ?: "Что-то пошло не так."))
    }

    /**
     * Formats an error message and returns it as a string.
     * @param message Message to format (a format string).
     * @param args Arguments to format the message with.
     * @return This message, formatted with arguments, with an error badge prepended.
     */
    @JvmStatic
    fun formatError(message: String, vararg args: Any?): String {
        return ERROR_BADGE+message.format(*args)
    }

    @JvmStatic
    fun inviteInput(isServerInputInvitation: Boolean = false, userName: String = "JohnDoe") {
        if (isServerInputInvitation) {
            print("$ESCAPE_CHAR[34;1mserver@SpaceMarineManager # $ESCAPE_CHAR[0m")
        } else {
            print("$ESCAPE_CHAR[32;1m$userName@SpaceMarineManager $ $ESCAPE_CHAR[0m")
        }
    }
}