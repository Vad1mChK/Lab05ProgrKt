package ru.vad1mchk.progr.lab05.common.io

import java.io.PrintWriter

/**
 * Object that manages terminal output.
 */
object Printer {
    private const val ESCAPE_CHAR = 27.toChar()
    private const val ERROR_BADGE = "[$ESCAPE_CHAR[31;1mОШИБКА$ESCAPE_CHAR[0m] "

    @JvmStatic
    fun printNoNewLine(message: String, vararg args: Any?) {
        print(message.format(*args))
    }

    @JvmStatic
    fun printNewLine(message: String, vararg args: Any?) {
        println(message.format(*args))
    }

    @JvmStatic
    fun printError(message: String) {
        println(ERROR_BADGE+message)
    }

    @JvmStatic
    fun printError(e: Throwable) {
        println(ERROR_BADGE+(e.message ?: "Что-то пошло не так."))
    }

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