package ru.vad1mchk.progr.lab05.client.messages

/**
 * Object to store messages and all string resources,
 * including formats and regex strings.
 */
object Messages {
    private const val escapeCharacter = 27.toChar()

    const val badgeError = "[$escapeCharacter[91;1mERROR$escapeCharacter[0m] "
    const val badgeException = "[$escapeCharacter[91;1mEXCEPTION$escapeCharacter[0m] "
    const val badgeInfo = "[$escapeCharacter[94;1mINFO$escapeCharacter[0m] "
    const val badgeWarning = "[$escapeCharacter[93;1mWARNING$escapeCharacter[0m] "

    const val errorFileAccessRead = "Cannot read file: not enough permissions."
    const val errorFileAccessWrite = "Cannot write file: not enough permissions."
    const val errorFileAccessReadWrite = "Cannot read or write file: not enough permissions."
    const val errorFileCannotCreateIO =
        "Cannot create file: I/O exception has occurred, or parent directory does not exist."
    const val errorFileCannotCreateAlreadyExists = "Cannot create file: file already exists."
    const val errorFileNotCSV = "Cannot open file: collection file does not have the CSV extension."
    const val errorTooManyProgramArguments = "Too many program arguments. The program will now be finished."

    const val exceptionDataInvalidDateFormat = "Cannot parse date: date format is invalid."
    const val exceptionDataInvalidEnumConstant = "Cannot parse field: should be one of the enum constants %s or null."
    const val exceptionDataInvalidNotNull = "Cannot parse field: should not be null nor empty."
    const val exceptionDataInvalidNumberFormat = "Cannot parse field: should be a number."
    const val exceptionDataInvalidBoolean = "Cannot parse field: should be a boolean."
    const val exceptionDataInvalidNumberShouldBeFinite = "Cannot parse field: number should be finite."
    const val exceptionDataInvalidNumberShouldBeGreaterThanFloat = "Cannot parse field: number should be > %f."
    const val exceptionDataInvalidNumberShouldBeGreaterThanInt = "Cannot parse field: number should be > %d."
    const val exceptionDataInvalidNumberShouldBeInRangeIntOrLong =
        "Cannot parse field: number should be in range (%d; %d)."
    const val exceptionFileNotExists = "Cannot open file: file does not exist."
    const val exceptionInvalidCommandArgumentShouldBeInt = "Cannot execute command: argument should be int."
    const val exceptionInvalidCommandArgumentPathNotAccessible =
        "Cannot execute command: argument should be an accessible path."
    const val exceptionMalformedElement = "Cannot parse element: the entry in the collection file was malformed."
    const val exceptionMissingCommandArgument = "Cannot execute command: missing an argument."
    const val exceptionIdentifierCollision = "Cannot add element: id %d already exists in collection."
    const val exceptionIdentifierNotExists = "Cannot update/remove element: id %d does not exist in collection."
    const val exceptionRecursiveScriptCall = "Cannot execute script: attempted to call a script recursively."
    const val exceptionInvalidCommandName = "Cannot execute command: invalid command name \"%s\"."
    const val exceptionEndProgram = "Exiting program."


    const val warningNoProgramArguments =
        "No collection file was specified. Enter a path if you want to try to create a new file, or leave blank if you want to exit."
    const val warningUnspecifiedInitializationDate =
        "No initialization date was specified. Assuming the collection is initialized now..."
    const val warningNullMeleeWeapon = "If you leave the input blank, the melee weapon will be treated as null."
    const val warningNullChapter = "If you leave the input blank, the chapter will be treated as null."
    const val warningNullChapterParentLegion =
        "If you leave the input blank, the parent legion will be treated as null."

    const val formatCoordinates = "Coordinates (%d, %f)"
    const val formatLocalDate = "dd.MM.yyyy"
    val formatChapter = "\n" + """
        name: %s
        parent legion: %s
        marines count: %d
    """.trimIndent().prependIndent("\t\t")
    val formatSpaceMarine = """
        %s (id: %d):
            coordinates: %s
            creation date: %s
            health: %f
            heart count: %d
            loyal: %s
            melee weapon: %s
            chapter: %s
    """.trimIndent()

    const val regexSplitCSVString = ",(?=([^\"]*\"[^\"]*\")*[^\"]*\$)"

    val greetingString = """
        ${escapeCharacter}[1mSpace Marine Manager${escapeCharacter}[0m
        (c) 2022 Vadim Chaykin (Vad1mChK)
        ***
        
    """.trimIndent()
    val helpString = """
        help
            Prints help for available commands.
        info
            Prints information about the collection.
        show
            Prints all elements of the collection to the standard output.
        add {element}
            Add an element to the collection.
        update id {element}
            Updates an element by the specified id.
        remove_by_id id
            Removes an element by the specified id.
        clear
            Completely clears the collection.
        save
            Saves the collection into the collection file.
        execute_script file_name
            Executes a script from an external file.
        exit
            Exits the program without saving.
        add_if_min {element}
            Adds an element if it's less than all the others.
        remove_greater {element}
            Removes all elements greater than this element.
        history
            Prints several last executed commands.
        filter_less_than_melee_weapon meleeWeapon
            Prints all elements that have the meleeWeapon field less than this.
        filter_greater_than_heart_count heartCount
            Prints all elements that have the heartCount field greater than this.
        print_field_descending_health
            Prints health values of all elements sorted descending.
    """.trimIndent()
    const val historyString = "Last %d commands in history:"
    val infoString = """
        Collection type: %s
        Elements type: %s
        Size: %d 
        Initialization date: %s
    """.trimIndent()

    const val inputName = "Input name (non-empty string):"
    const val inputCoordinateX = "Input coordinate x (int > %d):"
    const val inputCoordinateY = "Input coordinate y (finite float):"
    const val inputHealth = "Input health (finite double > %f):"
    const val inputHeartCount = "Input heart count (long in range (%d..%d)):"
    const val inputLoyal = "Input if it is loyal (boolean):"
    const val inputMeleeWeapon = "Input melee weapon (one of the following constants: %s):"
    const val inputChapterName = "Input chapter name (non-empty string):"
    const val inputChapterParentLegion = "Input chapter parent legion (non-empty string):"
    const val inputChapterMarinesCount = "Input chapter marines count (int in range (%d..%d)):"

    const val inputInvitation = "Enter command $ "

    const val successScriptExecution = "Successfully executed script: %s."
}