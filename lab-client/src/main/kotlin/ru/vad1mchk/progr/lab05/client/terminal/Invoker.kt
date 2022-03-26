package ru.vad1mchk.progr.lab05.client.terminal

import ru.vad1mchk.progr.lab05.client.collection.SpaceMarineCollectionManager
import ru.vad1mchk.progr.lab05.client.command.Command
import ru.vad1mchk.progr.lab05.client.command.CommandWrapper
import ru.vad1mchk.progr.lab05.client.csv.Serializer
import ru.vad1mchk.progr.lab05.client.datatypes.MeleeWeapon
import ru.vad1mchk.progr.lab05.client.exceptions.*
import ru.vad1mchk.progr.lab05.client.io.ConsoleInputManager
import ru.vad1mchk.progr.lab05.client.io.FileInputManager
import ru.vad1mchk.progr.lab05.client.file.FileManager
import ru.vad1mchk.progr.lab05.client.io.InputManager
import ru.vad1mchk.progr.lab05.client.io.OutputManager
import ru.vad1mchk.progr.lab05.client.messages.Messages
import java.nio.file.FileSystems
import java.nio.file.Path
import java.util.*


/**
 * Object that invokes all the commands, also switches
 * between console mode and script mode and controls
 * pretty much everything.
 */
class Invoker(var inputManager: InputManager) {
    private val commandMap = HashMap<String, Command>()
    private var isRunning = false

    companion object {
        private val pathStack = Stack<Path>()
    }

    private var currentScriptPath: Path?

    init {
        currentScriptPath = null

        register("help", object : Command {
            override fun invoke(arg: String?) {
                help()
            }
        })

        register("info", object : Command {
            override fun invoke(arg: String?) {
                SpaceMarineCollectionManager.info()
            }
        })

        register("show", object : Command {
            override fun invoke(arg: String?) {
                SpaceMarineCollectionManager.show()
            }
        })

        register("add", object : Command {
            override fun invoke(arg: String?) {
                SpaceMarineCollectionManager.add(inputManager.readSpaceMarine())
            }
        })

        register("update_by_id", object : Command {
            override fun invoke(arg: String?) {
                if (arg == null) {
                    throw MissingCommandArgumentException(Messages.exceptionMissingCommandArgument)
                }
                val id = try {
                    arg.toInt()
                } catch (e: NumberFormatException) {
                    throw InvalidCommandArgumentException(Messages.exceptionInvalidCommandArgumentShouldBeInt)
                }
                SpaceMarineCollectionManager.updateById(id, inputManager.readSpaceMarine())
            }
        })

        register("remove_by_id", object : Command {
            override fun invoke(arg: String?) {
                if (arg == null) {
                    throw MissingCommandArgumentException(Messages.exceptionMissingCommandArgument)
                }
                val id = try {
                    arg.toInt()
                } catch (e: NumberFormatException) {
                    throw InvalidCommandArgumentException(Messages.exceptionInvalidCommandArgumentShouldBeInt)
                }
                SpaceMarineCollectionManager.removeById(id)
            }
        })

        register("clear", object : Command {
            override fun invoke(arg: String?) {
                SpaceMarineCollectionManager.clear()
            }
        })

        register("save", object : Command {
            override fun invoke(arg: String?) {
                Serializer.save()
            }
        })

        register("execute_script", object : Command {
            override fun invoke(arg: String?) {
                if (arg == null) {
                    throw MissingCommandArgumentException(Messages.exceptionMissingCommandArgument)
                }
                val path = try {
                    FileSystems.getDefault().getPath(arg).also {
                        FileManager.openFile(it, true, false)
                    }
                } catch (e: FileAccessException) {
                    throw InvalidCommandArgumentException(Messages.exceptionInvalidCommandArgumentPathNotAccessible)
                }
                if (path in pathStack) {
                    throw RecursiveScriptCallException(Messages.exceptionRecursiveScriptCall)
                }
                pathStack.push(path)
                val process: Invoker = Invoker(inputManager)
                process.fileMode(path)
                pathStack.pop()
                OutputManager.sayInfo(Messages.successScriptExecution, path.fileName)
            }
        })

        register("exit", object : Command {
            override fun invoke(arg: String?) {
                throw EndProgramException(Messages.exceptionEndProgram)
            }
        })

        register("add_if_min", object : Command {
            override fun invoke(arg: String?) {
                SpaceMarineCollectionManager.addIfMin(inputManager.readSpaceMarine())
            }
        })

        register("remove_greater", object : Command {
            override fun invoke(arg: String?) {
                SpaceMarineCollectionManager.removeGreater(inputManager.readSpaceMarine())
            }
        })

        register("history", object : Command {
            override fun invoke(arg: String?) {
                HistoryStorage.print()
            }
        })

        register("filter_less_than_melee_weapon", object : Command {
            override fun invoke(arg: String?) {
                if (arg == null) throw MissingCommandArgumentException(Messages.exceptionMissingCommandArgument)
                val meleeWeapon = try {
                    MeleeWeapon.valueOf(arg)
                } catch (e: IllegalArgumentException) {
                    throw InvalidDataException(
                        String.format(
                            Messages.exceptionDataInvalidEnumConstant, MeleeWeapon.listConstants()
                        )
                    )
                }
                SpaceMarineCollectionManager.filterLessThanMeleeWeapon(
                    meleeWeapon
                )
            }
        })

        register("filter_greater_than_heart_count", object : Command {
            override fun invoke(arg: String?) {
                if (arg == null) throw MissingCommandArgumentException(Messages.exceptionMissingCommandArgument)
                val heartCount = try {
                    arg.toLong()
                } catch (e: NumberFormatException) {
                    throw InvalidDataException(
                        String.format(
                            Messages.exceptionDataInvalidNumberFormat
                        )
                    )
                }
                SpaceMarineCollectionManager.filterGreaterThanHeartCount(
                    heartCount
                )
            }
        })

        register("print_field_descending_health", object : Command {
            override fun invoke(arg: String?) {
                SpaceMarineCollectionManager.printFieldDescendingHealth()
            }
        })

        register("", object : Command {
            override fun invoke(arg: String?) {
            }
        })
    }

    /**
     * Registers the command in the command map, so
     * it can be invoked later using execute function.
     *
     * @param commandName
     * @param command
     */
    private fun register(commandName: String, command: Command) {
        commandMap[commandName] = command
    }

    /**
     * Checks if the command by a specified name is registered.
     *
     * @param commandName Name of command to check.
     * @return `true` if it is registered, else `false`.
     */
    private fun isRegistered(commandName: String?): Boolean {
        return commandMap.containsKey(commandName)
    }


    /**
     * Switches to console mode. Receives input from user through a
     * command-line interface.
     */
    fun consoleMode() {
        inputManager = ConsoleInputManager()
        isRunning = true
        while (isRunning) {
            OutputManager.inviteInput()
            try {
                val pair: CommandWrapper = inputManager.readCommand()
                runCommand(pair.commandName, pair.argument)
            } catch (e: EndProgramException) {
                OutputManager.say(e.message!!)
                isRunning = false
            }
        }
    }

    /**
     * Switches to file mode. Opens the script with the specified path.
     * @param path Path of the file to open.
     */
    fun fileMode(path: Path) {
        inputManager = FileInputManager(path)
        isRunning = true
        while (isRunning && inputManager.scanner().hasNextLine()) {
            try {
                val pair: CommandWrapper = inputManager.readCommand()
                runCommand(pair.commandName, pair.argument)
            } catch (e: EndProgramException) {
                OutputManager.say(e.message!!)
                isRunning = false
            }
        }
    }

    /**
     * Runs the command with an argument (if it is not
     * null).
     * @param commandName Name of the command to run.
     * @param argument Command argument.
     */
    fun runCommand(commandName: String, argument: String?) {
        try {
            if (!isRegistered(commandName)) throw InvalidCommandNameException(
                String.format(
                    Messages.exceptionInvalidCommandName,
                    commandName
                )
            )
            commandMap[commandName]!!(argument)
            HistoryStorage.add(commandName)
        } catch (e: CommandException) {
            OutputManager.sayException(e)
        } catch (e: InvalidDataException) {
            OutputManager.sayException(e)
        } catch (e: FileException) {
            OutputManager.sayError(e)
        } catch (e: EndProgramException) {
            throw e
        }
    }

    /**
     * Runs the command without an argument.
     * @param commandName Name of the command to run.
     */
    fun runCommand(commandName: String) {
        runCommand(commandName, null)
    }

    /**
     * Prints help for available commands.
     */
    fun help() {
        OutputManager.say(Messages.helpString)
    }
}