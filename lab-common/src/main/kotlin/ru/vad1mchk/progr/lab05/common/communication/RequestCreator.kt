package ru.vad1mchk.progr.lab05.common.communication

import ru.vad1mchk.progr.lab05.common.commands.AvailableCommands
import ru.vad1mchk.progr.lab05.common.datatypes.MeleeWeapon
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.common.io.SpaceMarineDataReader
import java.util.Scanner

class RequestCreator(scanner: Scanner = Scanner(System.`in`)) {
    val scanner: Scanner

    init {
        this.scanner = scanner
    }

    fun requestFromEnteredCommand(enteredCommand: EnteredCommand): Request? {
        return if (
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
            !AvailableCommands.isCommandNameAvailable(enteredCommand.name)
        ) {
            Printer.printError("Команда ${enteredCommand.name} не существует или недоступна.")
            null
        } else {
            Printer.printError("Неверное количество аргументов команды ${enteredCommand.name}.")
            null
        }
    }

    private fun spaceMarineRequest(enteredCommand: EnteredCommand): Request {
        return Request(enteredCommand.name, SpaceMarineDataReader(scanner).readMarine())
    }

    private fun idRequest(enteredCommand: EnteredCommand): Request? {
        val id = try {
            enteredCommand.arguments[0].toInt()
        } catch (e: NumberFormatException) {
            Printer.printError("Значение аргумента \"id\" для команды ${enteredCommand.name} должно быть целым числом.")
            return null
        }
        return Request(enteredCommand.name, idArgument = id)
    }

    private fun idAndSpaceMarineRequest(enteredCommand: EnteredCommand): Request? {
        val id = try {
            enteredCommand.arguments[0].toInt()
        } catch (e: NumberFormatException) {
            Printer.printError("Значение аргумента \"id\" для команды ${enteredCommand.name} должно быть целым числом.")
            return null
        }
        return Request(enteredCommand.name, SpaceMarineDataReader(scanner).readMarine(), id)
    }

    private fun meleeWeaponRequest(enteredCommand: EnteredCommand): Request? {
        val meleeWeapon = try {
            MeleeWeapon.valueOf(enteredCommand.arguments[0])
        } catch (e: IllegalArgumentException) {
            Printer.printError("Значение аргумента \"meleeWeapon\" для команды ${enteredCommand.name} должно быть из ${
                MeleeWeapon.listAllConstants()
            }.")
            return null
        }
        return Request(enteredCommand.name, meleeWeaponArgument = meleeWeapon)
    }

    private fun heartCountRequest(enteredCommand: EnteredCommand): Request? {
        val heartCount = try {
            enteredCommand.arguments[0].toLong()
        } catch (e: NumberFormatException) {
            Printer.printError("Значение аргумента \"heartCount\" для команды ${
                enteredCommand.name
            } должно быть целым числом.")
            return null
        }
        return Request(enteredCommand.name, heartCountArgument = heartCount)
    }
}