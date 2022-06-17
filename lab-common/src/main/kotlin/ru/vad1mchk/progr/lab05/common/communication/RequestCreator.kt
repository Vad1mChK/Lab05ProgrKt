package ru.vad1mchk.progr.lab05.common.communication

import ru.vad1mchk.progr.lab05.common.commands.AvailableCommands
import ru.vad1mchk.progr.lab05.common.datatypes.MeleeWeapon
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.common.datatypes.User
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.common.io.SpaceMarineDataReader
import ru.vad1mchk.progr.lab05.common.io.UserDataReader
import java.util.*

/**
 * Class that creates requests from entered commands. Determines which types of arguments the command uses and
 * uses a scanner to enter data types that are neither standard nor enum constants.
 *
 * @property scanner Scanner to use.
 */
class RequestCreator(
    private val user: User? = null,
    private val scanner: Scanner = Scanner(System.`in`),
    private val printer: Printer
) {
    val spaceMarineDataReader = SpaceMarineDataReader(user, scanner, printer)
    val userDataReader = UserDataReader(System.console(), printer)

    /**
     * Creates a request from the entered command, inferring its arguments types from the name
     * @param enteredCommand Entered command to create the request from.
     * @return The request if it was successfully created, else `null`.
     */
    fun requestFromEnteredCommand(enteredCommand: EnteredCommand): Request? {
        return if (enteredCommand.name.isBlank()) null
        else if (
            AvailableCommands.COMMANDS_WITHOUT_ARGUMENTS.contains(enteredCommand.name)
            && enteredCommand.arguments.isEmpty()
            || AvailableCommands.COMMANDS_WITH_FILE_NAME_ARGUMENT.contains(enteredCommand.name)
            && enteredCommand.arguments.size == 1
        ) {
            Request(enteredCommand.name)
        } else if (
            AvailableCommands.COMMANDS_WITH_SPACE_MARINE_ARGUMENT.contains(enteredCommand.name)
            && enteredCommand.arguments.isEmpty()
        ) {
            spaceMarineRequest(enteredCommand)
        } else if (
            AvailableCommands.COMMANDS_WITH_ID_ARGUMENT.contains(enteredCommand.name)
            && enteredCommand.arguments.size == 1
        ) {
            idRequest(enteredCommand)
        } else if (
            AvailableCommands.COMMANDS_WITH_ID_AND_SPACE_MARINE_ARGUMENTS.contains(enteredCommand.name)
            && enteredCommand.arguments.size == 1
        ) {
            idAndSpaceMarineRequest(enteredCommand)
        } else if (
            AvailableCommands.COMMANDS_WITH_MELEE_WEAPON_ARGUMENT.contains(enteredCommand.name)
            && enteredCommand.arguments.size == 1
        ) {
            meleeWeaponRequest(enteredCommand)
        } else if (
            AvailableCommands.COMMANDS_WITH_HEART_COUNT_ARGUMENT.contains(enteredCommand.name)
            && enteredCommand.arguments.size == 1
        ) {
            heartCountRequest(enteredCommand)
        } else if (
            AvailableCommands.COMMANDS_WITH_USER_ARGUMENT.contains(enteredCommand.name)
            && enteredCommand.arguments.isEmpty()
        ) {
            userRequest(enteredCommand)
        } else if (
            !AvailableCommands.isCommandNameAvailable(enteredCommand.name)
        ) {
            printer.printError("Команда ${enteredCommand.name} не существует или недоступна.")
            null
        } else {
            printer.printError("Неверное количество аргументов команды ${enteredCommand.name}.")
            null
        }
    }

    /**
     * Creates a request that takes an instance of [SpaceMarine] as an argument, reading the info about the space marine
     * separately using [SpaceMarineDataReader].
     * @param enteredCommand Entered command to create the request from.
     * @return A new request that has a non-null space marine argument.
     */
    private fun spaceMarineRequest(enteredCommand: EnteredCommand): Request {
        return Request(enteredCommand.name, spaceMarineDataReader.readMarine())
    }

    /**
     * Creates a request that takes a [Int] number as an ID argument.
     * @param enteredCommand Entered command to create the request from.
     * @return A new request that has a non-null ID argument.
     */
    private fun idRequest(enteredCommand: EnteredCommand): Request? {
        val id = try {
            enteredCommand.arguments[0].toInt()
        } catch (e: NumberFormatException) {
            printer.printError("Значение аргумента \"id\" для команды ${enteredCommand.name} должно быть целым числом.")
            return null
        }
        return Request(enteredCommand.name, idArgument = id)
    }

    /**
     * Creates a request that takes a [SpaceMarine] and an [Int] as arguments, reading the info about the former
     * separately using [SpaceMarineDataReader].
     * @param enteredCommand Entered command to create the request from.
     * @return A new request that has non-null space marine and ID arguments.
     */
    private fun idAndSpaceMarineRequest(enteredCommand: EnteredCommand): Request? {
        val id = try {
            enteredCommand.arguments[0].toInt()
        } catch (e: NumberFormatException) {
            printer.printError("Значение аргумента \"id\" для команды ${enteredCommand.name} должно быть целым числом.")
            return null
        }
        return Request(enteredCommand.name, spaceMarineDataReader.readMarine(), id)
    }

    /**
     * Creates a request that takes a [MeleeWeapon] as argument.
     * @param enteredCommand Entered command to create the request from.
     * @return A new request that has a non-null melee weapon argument.
     */
    private fun meleeWeaponRequest(enteredCommand: EnteredCommand): Request? {
        val meleeWeapon = try {
            MeleeWeapon.valueOf(enteredCommand.arguments[0])
        } catch (e: IllegalArgumentException) {
            printer.printError(
                "Значение аргумента \"meleeWeapon\" для команды ${enteredCommand.name} должно быть из ${
                    MeleeWeapon.listAllConstants()
                }."
            )
            return null
        }
        return Request(enteredCommand.name, meleeWeaponArgument = meleeWeapon)
    }

    /**
     * Creates a request that takes a [Long] number as hart count argument.
     * @param enteredCommand Entered command to create the request from.
     * @return A new request that has a non-null melee weapon argument.
     */
    private fun heartCountRequest(enteredCommand: EnteredCommand): Request? {
        val heartCount = try {
            enteredCommand.arguments[0].toLong()
        } catch (e: NumberFormatException) {
            printer.printError(
                "Значение аргумента \"heartCount\" для команды ${
                    enteredCommand.name
                } должно быть целым числом."
            )
            return null
        }
        return Request(enteredCommand.name, heartCountArgument = heartCount)
    }

    private fun userRequest(enteredCommand: EnteredCommand): Request {
        return Request(enteredCommand.name, user = userDataReader.readUser())
    }
}