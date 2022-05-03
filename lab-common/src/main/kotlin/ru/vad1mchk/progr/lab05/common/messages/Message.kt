package ru.vad1mchk.progr.lab05.common.messages

import org.fusesource.jansi.Ansi
import org.fusesource.jansi.Ansi.ansi
import java.io.Serializable

/**
 * Abstract message class. All messages have [format] and [type], some may have [arguments].
 * @property type Message type.
 * @property text Message format.
 * @property arguments Message arguments.
 */
class Message : Serializable {
    private val format: String
    private val arguments: Array<out Any?>
    private val type: MessageType

    /**
     * Type of the message. Each type has its unique badge when printed, and the behavior of the program may depend on
     * this type.
     */
    enum class MessageType {
        /**
         * This type signifies that some serious error has occurred.
         */
        ERROR,

        /**
         * This type signifies that some non-fatal exception has occurred, and it can be handled.
         */
        EXCEPTION,

        /**
         * This type is typically used for information messages, such as collection info or success messages.
         */
        INFO,

        /**
         * This type is used for messages that belong to neither category.
         */
        NORMAL,

        /**
         * This type is used to warn the user of actions that may have undesired consequences.
         */
        WARNING
    }

    companion object {
        val stringResources = StringResources()
    }

    constructor(format: String, vararg arguments: Any?) {
        this.type = MessageType.NORMAL
        this.format = format
        this.arguments = arguments
    }

    constructor(type: MessageType, format: String, vararg arguments: Any?) {
        this.type = type
        this.format = format
        this.arguments = arguments
    }

    /**
     * Returns the text of the message, already formatted with the badge and all necessary arguments.
     * @return Full text of the message.
     */
    fun getText(): String {
        return when (type) {
            MessageType.ERROR -> {
                ansi().reset().a('[').fg(Ansi.Color.RED).bold().a(stringResources.getString("error badge"))
                    .reset().a("] ")
            }
            MessageType.EXCEPTION -> {
                ansi().reset().a('[').fg(Ansi.Color.RED).bold().a(stringResources.getString("exception badge"))
                    .reset().a("] ")
            }
            MessageType.INFO -> {
                ansi().reset().a('[').fg(Ansi.Color.CYAN).bold().a(stringResources.getString("info badge"))
                    .reset().a("] ")
            }
            MessageType.WARNING -> {
                ansi().reset().a('[').fg(Ansi.Color.YELLOW).bold().a(stringResources.getString("warning badge"))
                    .reset().a("] ")
            }
            else -> ansi().reset()
        }.toString() + String.format(format, *arguments)
    }
}