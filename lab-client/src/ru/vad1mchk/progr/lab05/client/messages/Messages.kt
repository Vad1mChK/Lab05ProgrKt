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

    private val map = hashMapOf<String, String>(
        "fatalErrorNoProgramArguments" to "No arguments given: should be exactly one (collection path)",
        "fatalErrorTooManyProgramArguments" to "Too many arguments (%d) given: should be exactly one (collection path)",
        "errorCollectionNotFound" to "Collection cannot be found",
        "errorIDCollision" to "Element with ID %d already exists, newer element ignored",
        "errorInvalidCommandName" to "No such command: %s",
        "errorInvalidEnumConstant" to "None of the constants: %s match",
        "errorInvalidFieldFormatCoordinates" to "Invalid coordinates format, should be x:y",
        "errorInvalidFieldFormatDate" to "Invalid date format, should be DD.MM.YYYY",
        "errorInvalidFieldTypeCoordinates" to "Coordinates fields should be of types int and float",
        "errorInvalidFieldTypeChapterMarineCount" to "Chapter marine count should be of type int",
        "errorInvalidFieldValueChapterName" to "Chapter name should not be null nor empty",
        "errorInvalidFieldValueChapterMarineCountNull" to "Chapter marine count should not be null",
        "errorValidationCoordinateX" to "Coordinates should be greater than %d",
        "errorValidationSpaceMarineID" to "ID should not be less than %d",
        "errorValidationSpaceMarineHealth" to "Health should be greater than %f",
        "errorValidationSpaceMarineHeartCount" to "Heart count should be in range (%d; %d]",
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
        "infoSpaceMarineRepresentationFormatString" to """
            $escapeCharacter[3m%s$escapeCharacter[0m [%d]:
                coordinates: %s,
                creation date: %s,
                health: %.2f,
                heart count: %d,
                %s,
                melee weapon: %s,
                chapter: %s
        """.trimIndent(),
        "infoChapterRepresentationFormatString" to "Chapter (name: %s, parent legion: %s, marines count: %d)",
        "inputMarineName" to "Enter name ($escapeCharacter[3mnon-empty string$escapeCharacter[0m): ",
        "inputCoordinateX" to "Enter coordinate x ($escapeCharacter[3mint > %d $escapeCharacter[0m): ",
        "inputCoordinateY" to "Enter coordinate y ($escapeCharacter[3mfloat$escapeCharacter[0m): ",
        "inputMarineHealth" to "Enter health ($escapeCharacter[3mfloat > 0.0$escapeCharacter[0m): ",
        "inputMarineHeartCount" to "Enter heart count ($escapeCharacter[3m1 <= long <= 3$escapeCharacter[0m): ",
        "inputMarineLoyal" to "Enter if it is loyal ($escapeCharacter[3mboolean$escapeCharacter[0m): ",
        "inputMarineMeleeWeapon" to "Enter melee weapon ($escapeCharacter[3mone of the following constants: {%s}$escapeCharacter[0m, leave blank for null):",
        "inputMarineChapterName" to "Enter chapter name (leave blank if you don't want to input chapter): ",
        "inputMarineChapterParentLegion" to "Enter chapter parent legion (leave blank for null): ",
        "inputMarineChapterMarinesCount" to "Enter chapter marines count (%d < int < %d): ",
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

    fun say(messageFormat: String, level: Level = Level.NONE, vararg args: Any?) {
        println(level.badge + String.format(messageFormat, *args))
    }
}