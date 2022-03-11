package ru.vad1mchk.progr.lab05.client.messages

import ru.vad1mchk.progr.lab05.client.exceptions.NoSuchMessageError

object Messages {
    private val escapeCharacter: Char = Char(27)

    enum class Level(val badge: String) {
        ERROR("[$escapeCharacter[91;1mERROR$escapeCharacter[0m] "),
        WARNING("[$escapeCharacter[33;1mWARNING$escapeCharacter[0m] "),
        INFO("[$escapeCharacter[34;1mINFO$escapeCharacter[0m] "),
        DEBUG("[$escapeCharacter[32;1mDEBUG$escapeCharacter[0m] "),
        FATAL_ERROR("[$escapeCharacter[91;1mFATAL ERROR$escapeCharacter[0m] "),
        NONE("");
    }

    val map = hashMapOf<String, String>(
        "fatalErrorNoProgramArguments" to "No arguments given: should be exactly one, collection path",
        "fatalErrorTooManyProgramArguments" to "Too many arguments (%d) given: should be exactly one, collection path",
        "errorCollectionNotFound" to "Collection cannot be found",
        "errorInvalidFieldFormatDate" to "Invalid date format, should be DD.MM.YYYY",
        "helpString" to """
            help
                Print help for available commands.
            info
                Print information about the collection.
            show
                Print all elements of the collection.
            add {element}
                Add a new element to the collection.
            update id {element}
                Update the value of the element with specified id.
            remove_by_id id
                Remove element by specified id.
            clear
                Clear the collection.
            save
                Save the collection to the file.
            execute_script file_name
                Read and execute script from an external file.
            exit
                Terminate the program without saving.
            add_if_min {element}
                Add a new element to the collection if its value is less than all others.
            remove_greater {element}
                Remove all elements greater than this from the collection.
            history
                Output last 12 command names.
            filter_less_than_melee_weapon meleeWeapon
                Print all elements that have meleeWeapon field less than given.
            filter_greater_than_heart_count heartCount
                Print all elements that have heartCount field greater than given.
            print_field_descending_health
                Print all health fields in descending order.
        """.trimIndent(),
        "infoFormatString" to """
            Collection (
                collection type: %s,
                elements type: %s,
                size: %d,
                initialization date: %s
            )
        """.trimIndent(),
    )

    operator fun get(key: String): String {
        if (!map.containsKey(key)) {
            throw NoSuchMessageError("Message with key \"$key\" not found")
        }
        if (map[key] == null) {
            throw NoSuchMessageError("Message with key \"$key\" is null")
        }
        return map[key]!!
    }

    fun say(level: Level = Level.NONE, messageFormat: String, vararg args: Any?) {
        println(level.badge + String.format(messageFormat, *args))
    }

    fun sayByKey(level: Level = Level.NONE, messageKey: String, vararg args: Any?) {
        println(level.badge + String.format(this[messageKey], *args))
    }

}