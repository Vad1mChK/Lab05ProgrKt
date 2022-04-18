package ru.vad1mchk.progr.lab05.common.command

import ru.vad1mchk.progr.lab05.common.connection.CommandMessage
import ru.vad1mchk.progr.lab05.common.connection.ResponseMessage
import ru.vad1mchk.progr.lab05.common.connection.Status
import ru.vad1mchk.progr.lab05.common.exceptions.*
import ru.vad1mchk.progr.lab05.common.io.InputManager
import ru.vad1mchk.progr.lab05.common.io.OutputManager
import ru.vad1mchk.progr.lab05.common.io.ScriptInputManager
import ru.vad1mchk.progr.lab05.common.io.TerminalInputManager
import ru.vad1mchk.progr.lab05.common.messages.Messages
import java.nio.file.Path
import java.util.*

/**
 * Class that stores and executes commands, and switches between reading input from terminal and scripts.
 */
abstract class Commander : AutoCloseable {
    companion object {
        val pathStack = Stack<Path>()
    }
    private val commandMap = TreeMap<String, Command>()
    private lateinit var inputManager: InputManager
    private var isRunning: Boolean = false
    var currentScriptPath: Path? = null
        private set

    /**
     * Registers the command in the command map, so it can be invoked later using execute function.
     * @param key Name of the command to call it by.
     * @param command Command to register.
     */
    fun register(key: String, command: Command) {
        commandMap[key] = command
    }

    /**
     * Checks if the command by a specified name is registered.
     * @param key Name of command to check.
     * @return `true` if it is registered, else `false`.
     */
    fun isRegistered(key: String): Boolean {
        return key in commandMap.keys
    }

    /**
     * Gets the command from the command map, failing if it is unregistered.
     * @param key Name of the command to get.
     * @return The command if it was found in the command map.
     * @throws InvalidCommandNameException if it was not found in the command map.
     */
    @Throws(InvalidCommandNameException::class)
    fun getCommand(key: String): Command {
        if (key !in commandMap.keys) {
            throw InvalidCommandNameException(
                String.format(
                    Messages.exceptionInvalidCommandName,
                    key
                )
            )
        }
        return commandMap[key]!!
    }

    /**
     * Switches to terminal mode. Receives input from user through a command-line interface.
     */
    fun terminalMode() {
        inputManager = TerminalInputManager()
        isRunning = true
        loop(true)

    }

    /**
     * Switches to script mode. Opens the script with the specified path.
     * @param path Path of the file to open.
     */
    fun scriptMode(path: Path) {
        currentScriptPath = path
        inputManager = ScriptInputManager(path)
        isRunning = true
        loop(false)
    }

    /**
     * Inner loop that is called in both modes.
     * @param inviteInput If the input invitation needs to be displayed.
     */
    private fun loop(inviteInput: Boolean) {
        try {
            while (isRunning) {
                if (inviteInput) OutputManager.inviteInput()
                val responseMessage = runCommand(inputManager.readCommand())
                if (responseMessage.status == Status.EXIT) {
                    close()
                }
            }
        } catch (e: EndProgramException) {
            close()
        }
    }

    /**
     * Runs the command parsed from this command message.
     * @param commandMessage Command message (request) to obtain the command from.
     * @return Response that shows how the command was executed.
     */
    abstract fun runCommand(commandMessage: CommandMessage): ResponseMessage

    /**
     * Prints help for all available commands in alphabetical order.
     */
    fun printHelp() {
        for (key in commandMap.keys) {
            println(key+commandMap[key]!!.help())
        }
    }

    override fun close() {
        isRunning = false
    }
}