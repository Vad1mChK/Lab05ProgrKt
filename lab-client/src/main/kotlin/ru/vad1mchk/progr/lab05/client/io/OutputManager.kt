package ru.vad1mchk.progr.lab05.client.io

import ru.vad1mchk.progr.lab05.client.messages.Messages
import java.util.*

/**
 * Object to work with console output. Has pre-defined
 * methods to print errors, exceptions, information,
 * and warnings.
 */
object OutputManager {

    /**
     * Prints a message just like `System.out.printf()`,
     * but with newline.
     * @param format Format string.
     * @param args Optional arguments.
     */
    fun say(format: String, vararg args: Any?) {
        println(String.format(Locale.ROOT, format, *args))
    }

    /**
     * Prints a message from a caught exception (if it had any)
     * with an ERROR badge on it.
     * @param format Format string.
     * @param args Optional arguments.
     */
    fun sayError(format: String, vararg args: Any?) {
        println(Messages.badgeError + String.format(format, *args))
    }

    /**
     * Prints a message from a caught exception (if it had any)
     * with an ERROR badge on it.
     * @param e Exception to get the message from.
     */
    fun sayError(e: Exception) {
        say(Messages.badgeError + (e.message ?: ""))
    }

    /**
     * Prints a message from a caught exception (if it had any)
     * with an EXCEPTION badge on it.
     * @param format Format string.
     * @param args Optional arguments.
     */
    fun sayException(format: String, vararg args: Any?) {
        println(Messages.badgeException + String.format(format, *args))
    }

    /**
     * Prints a message from a caught exception (if it had any)
     * with an ERROR badge on it.
     * @param e Exception to get the message from.
     */
    fun sayException(e: Exception) {
        say(Messages.badgeException + (e.message ?: ""))
    }

    /**
     * Prints a message with an INFO badge on it.
     * @param format Format string.
     * @param args Optional arguments.
     */
    fun sayInfo(format: String, vararg args: Any?) {
        println(Messages.badgeInfo + String.format(format, *args))
    }

    /**
     * Prints a message with a WARNING badge on it.
     * @param format Format string.
     * @param args Optional arguments.
     */
    fun sayWarning(format: String, vararg args: Any?) {
        println(Messages.badgeWarning + String.format(format, *args))
    }

    fun inviteInput() {
        print(Messages.inputInvitation)
    }
}