package ru.vad1mchk.progr.lab05.common.io

import org.fusesource.jansi.Ansi.ansi
import ru.vad1mchk.progr.lab05.common.messages.Message
import ru.vad1mchk.progr.lab05.common.messages.StringResources
import java.io.PrintStream

/**
 * Object to manage terminal output.
 */
object OutputManager {
    var printStream = PrintStream(System.`out`)
    private val stringResources = StringResources()

    /**
     * Prints a message, inferring the badge from its type.
     * @param message Message to print.
     */
    fun say(message: Message) {
        printStream.println(message.getText())
    }

    /**
     * A lightweight variation of [say] method -- only prints a string without having to create a new [Message] object.
     * @param messageText Text to print.
     */
    fun sayString(messageText: String,) {
        printStream.println(messageText)
    }

    /**
     * A lightweight variation of [say] method -- only prints a string without having to create a new [Message] object.
     * @param messageText Text to print.
     * @param messageArguments Arguments to format the string with.
     */
    fun sayString(messageText: String, vararg messageArguments: Any?) {
        printStream.println(messageText.format(* messageArguments))
    }

    /**
     * Prints a message of this error with the badge.
     * @param e Error to print.
     */
    fun sayError(e: Error) {
        printStream.println(
            ansi().a('[').fgRed().bold().a(stringResources.getString("error badge")).reset().a(e.message)
                .reset()
        )
    }

    /**
     * Prints text with the error badge.
     * @param messageText Text of message to print.
     * @param messageArguments Optional arguments of this message.
     */
    fun sayError(messageText: String, vararg messageArguments: Any?) {
        printStream.println(
            ansi().a('[').fgRed().bold().a(stringResources.getString("error badge")).reset().a("] ")
                .a(messageText.format(* messageArguments)).reset()
        )
    }

    /**
     * Prints a message of this exception with the badge.
     * @param e Exception to print.
     */
    fun sayException(e: Exception) {
        printStream.println(
            ansi().a('[').fgRed().bold().a(stringResources.getString("exception badge")).reset().a("] ")
                .a(e.message).reset()
        )
    }

    /**
     * Prints text with the exception badge.
     * @param messageText Text of message to print.
     * @param messageArguments Optional arguments of this message.
     */
    fun sayException(messageText: String, vararg messageArguments: Any?) {
        printStream.println(
            ansi().a('[').fgRed().bold().a(stringResources.getString("exception badge")).reset().a("] ")
                .a(messageText.format(* messageArguments)).reset()
        )
    }

    /**
     * Prints text with the warning badge.
     * @param messageText Text of message to print.
     * @param messageArguments Optional arguments of this message.
     */
    fun sayWarning(messageText: String, vararg messageArguments: Any?) {
        printStream.println(
            ansi().a('[').fgYellow().bold().a(stringResources.getString("warning badge")).reset().a("] ")
                .a(messageText.format(* messageArguments)).reset()
        )
    }

    /**
     * Prints text with the info badge.
     * @param messageText Text of message to print.
     * @param messageArguments Optional arguments of this message.
     */
    fun sayInfo(messageText: String, vararg messageArguments: Any?) {
        printStream.println(
            ansi().a('[').fgCyan().bold().a(stringResources.getString("info badge")).reset().a("] ")
                .a(messageText.format(* messageArguments)).reset()
        )
    }

    /**
     * Displays a greeting sequence to the user.
     * @param isServer If the user is on the server side.
     */
    fun greet(isServer: Boolean = false) {
        printStream.println(stringResources.getString("greeting ${if (isServer) "server" else "client"}"))
    }

    /**
     * Prints input invitation for the next command of the user.
     * @param isServer If the user is on the server side.
     */
    fun inviteInput(isServer: Boolean = false) {
        printStream.print(
            stringResources.getString("input invitation ${if (isServer) "server" else "client"}")
        )
    }
}