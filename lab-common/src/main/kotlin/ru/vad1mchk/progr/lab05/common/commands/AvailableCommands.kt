package ru.vad1mchk.progr.lab05.common.commands

/**
 * Class that contains list of available command names grouped in sets by types of the command arguments.
 */
object AvailableCommands {
    val COMMANDS_WITHOUT_ARGUMENTS = sortedSetOf<String>(
        "help", "info", "show", "users", "clear", "exit", "execute_script", "history", "print_field_descending_health"
    )

    val COMMANDS_WITH_SPACE_MARINE_ARGUMENT = sortedSetOf(
        "add", "add_if_min", "remove_greater"
    )

    val COMMANDS_WITH_ID_ARGUMENT = sortedSetOf(
        "remove_by_id", "get_by_id"
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

    val COMMANDS_WITH_USER_ARGUMENT = sortedSetOf(
        "register", "login"
    )

    /**
     * Checks if this command name is available.
     * @param commandName Name of the command to check.
     * @return `true` if this command is in one of the sets, else `false`.
     */
    @JvmStatic
    fun isCommandNameAvailable(commandName: String): Boolean {
        return commandName in COMMANDS_WITHOUT_ARGUMENTS +
                COMMANDS_WITH_SPACE_MARINE_ARGUMENT +
                COMMANDS_WITH_ID_ARGUMENT +
                COMMANDS_WITH_ID_AND_SPACE_MARINE_ARGUMENTS +
                COMMANDS_WITH_MELEE_WEAPON_ARGUMENT +
                COMMANDS_WITH_HEART_COUNT_ARGUMENT +
                COMMANDS_WITH_FILE_NAME_ARGUMENT +
                COMMANDS_WITH_USER_ARGUMENT
    }
}