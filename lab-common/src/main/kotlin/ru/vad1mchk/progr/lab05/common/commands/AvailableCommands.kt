package ru.vad1mchk.progr.lab05.common.commands

object AvailableCommands {
    val COMMANDS_WITHOUT_ARGUMENTS = sortedSetOf<String>(
        "help", "info", "show", "clear", "save", "exit", "execute_script", "history", "print_field_descending_health"
    )

    val COMMANDS_WITH_SPACE_MARINE_ARGUMENT = sortedSetOf(
        "add", "add_if_min", "remove_greater"
    )

    val COMMANDS_WITH_ID_ARGUMENT = sortedSetOf(
        "remove_by_id"
    )

    val COMMANDS_WITH_ID_AND_SPACE_MARINE_ARGUMENTS = sortedSetOf(
        "update"
    )

    val COMMANDS_WITH_MELEE_WEAPON_ARGUMENT = sortedSetOf(
        "filter_less_than_melee_weapon"
    )

    val COMMANDS_WITH_HEART_COUNT_ARGUMENT = sortedSetOf(
        "filter_greater_than_heart_count"
    )

    val COMMANDS_WITH_FILE_NAME_ARGUMENT = sortedSetOf(
        "execute_script"
    )

    @JvmStatic
    fun isCommandNameAvailable(commandName: String): Boolean {
        return commandName in COMMANDS_WITHOUT_ARGUMENTS
                || commandName in COMMANDS_WITH_SPACE_MARINE_ARGUMENT
                || commandName in COMMANDS_WITH_ID_ARGUMENT
                || commandName in COMMANDS_WITH_ID_AND_SPACE_MARINE_ARGUMENTS
                || commandName in COMMANDS_WITH_MELEE_WEAPON_ARGUMENT
                || commandName in COMMANDS_WITH_HEART_COUNT_ARGUMENT
                || commandName in COMMANDS_WITH_FILE_NAME_ARGUMENT
    }
}