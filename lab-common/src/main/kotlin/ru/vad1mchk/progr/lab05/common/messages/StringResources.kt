package ru.vad1mchk.progr.lab05.common.messages

import org.fusesource.jansi.Ansi.ansi
import java.util.*

/**
 * Class to store messages, formats and other string resources alike.
 */
class StringResources : ListResourceBundle() {
    companion object {
        private val contents: Array<Array<Any>> = arrayOf(
            // Badges
            arrayOf("exception badge", "EXCEPTION :("),
            arrayOf("error badge", "ERROR :|"),
            arrayOf("info badge", "INFO :)"),
            arrayOf("warning badge", "WARNING :o"),

            // Exceptions
            arrayOf("FileAccessException read", "Cannot read file: not enough permissions."),
            arrayOf("FileAccessException read or write", "Cannot read nor write file: not enough permissions."),
            arrayOf("FileAccessException write", "Cannot write file: not enough permissions."),
            arrayOf("FileCannotCreateException alreadyExists", "Cannot create file: file already exists."),
            arrayOf(
                "FileCannotCreateException IO",
                "Cannot create file: parent directory does not exist or IO exception occurred."
            ),
            arrayOf("FileNotCSVException", "Cannot open file: collection file does not have the CSV extension."),
            arrayOf("FileNotExistsException", "Cannot open file: file does not exist."),
            arrayOf("IdentifierCollisionException", "Cannot add element: id %d already exists in collection."),
            arrayOf(
                "IdentifierNotExistsException update",
                "Cannot update element: id %d does not exist in collection."
            ),
            arrayOf(
                "IdentifierNotExistsException remove",
                "Cannot remove element: id %d does not exist in collection."
            ),
            arrayOf("InvalidDataException boolean", "Cannot parse field: should be a boolean."),
            arrayOf("InvalidDataException enumConstant", "Cannot parse field: should be one of the enum constants %s."),
            arrayOf("InvalidDataException dateFormat", "Cannot parse date: date format is invalid."),
            arrayOf("InvalidDataException notNull", "Cannot parse field: should not be null."),
            arrayOf("InvalidDataException numberFormat", "Cannot parse field: should be a number."),
            arrayOf("InvalidDataException numberShouldBeFinite", "Cannot parse field: should be finite."),
            arrayOf(
                "InvalidDataException numberShouldBeInRange",
                "Cannot parse field: number should be in range (%s; %s)"
            ),
            arrayOf("InvalidDataException numberShouldBeGreaterThan", "Cannot parse field: should be greater than %s."),
            arrayOf(
                "InvalidDataException varargparse typeMismatch",
                "Cannot parse field: should be a value of type %s"
            ),
            arrayOf("IOException", "Sorry, an input/output exception has occurred."),
            arrayOf(
                "MalformedElementException",
                "Cannot parse element: the entry in the collection file was malformed."
            ),
            arrayOf(
                "UnknownHostException",
                "This address does not exist in this network."
            ),
            arrayOf(
                "UnsupportedOperationException cannotGetDate",
                "Cannot get initialization date from collection file, assuming it is initialized now."
            ),
            arrayOf("IOException cannotConnectToServer", "Cannot connect to the server: bad host or port name"),
            arrayOf("exception missingCommandArgument", "Cannot execute command: one or more arguments are missing."),
            arrayOf("exception cannotSendConnectionClosed", "Cannot send anything: connection is closed"),
            arrayOf("exception cannotReceiveConnectionClosed", "Cannot receive anything: connection is closed"),

            // Warnings
            arrayOf(
                "warning nullMeleeWeapon",
                "If you leave the next line blank, the melee weapon will be treated as null."
            ),
            arrayOf("warning nullChapter", "If you leave the next line blank, the chapter will be treated as null."),
            arrayOf(
                "warning nullParentLegion",
                "If you leave the next line blank, the chapter parent legion will be treated as null."
            ),
            arrayOf(
                "warning clearCollection",
                "This operation will clear the collection. Once the collection is saved, it will be impossible to undo."
            ),

            // Info messages
            arrayOf(
                "collection info", """
                Collection information:
                    Collection type: %s,
                    Element type: %s,
                    Size: %d,
                    Initialization date: %s
            """.trimIndent()
            ),
            arrayOf("show success", "Collection elements are as follows:"),
            arrayOf("add success", "Element added successfully."),
            arrayOf("addIfMin failure", "Element not added: some elements less or equal to this are in the collection."),
            arrayOf("update success", "Element updated successfully."),
            arrayOf("remove success", "Element removed successfully."),
            arrayOf("removeGreater success", "Elements removed successfully."),
            arrayOf("removeGreater failure", "Elements intact: none of them are greater than this."),
            arrayOf("clear success", "Collection cleared successfully."),
            arrayOf("save success", "Collection saved successfully."),
            arrayOf("connection success", "Connection established."),
            arrayOf("address found", "Address found."),

            // Command help
            arrayOf("help description", "Prints help for all available commands."),

            // Greetings and input invitations
            arrayOf(
                "greeting client", """
                ${ansi().bold().a("Space Marine Manager").reset().a(" (").fgGreen().a("Client Side")
                    .reset().a(")")}
                (c) 2022 Vadim Chaykin (Vad1mChK)
            """.trimIndent()
            ),
            arrayOf(
                "greeting server", """
                ${ansi().bold().a("Space Marine Manager").reset().a(" (").fgBlue().a("Server Side")
                    .reset().a(")")}
                (c) 2022 Vadim Chaykin (Vad1mChK)
            """.trimIndent()
            ),
            arrayOf("input inetAddress", "Enter address of the server: "),
            arrayOf("input port", "Enter port of the server: "),
            arrayOf("input invitation client", "${ansi().fgGreen().bold().a("client@SpaceMarineManager $ ")
                .reset()}"),
            arrayOf("input invitation server", "${ansi().fgBlue().bold().a("server@SpaceMarineManager # ").reset()}"),
            arrayOf("input name", "Enter name: "),
            arrayOf("input Coordinates x", "Enter coordinate x (int > %s): "),
            arrayOf("input Coordinates y", "Enter coordinate y (finite float): "),
            arrayOf("input health", "Enter health (finite double > %s): "),
            arrayOf("input heartCount", "Enter heart count (%s <= long <= %s): "),
            arrayOf("input loyal", "Enter if it is loyal (boolean): "),
            arrayOf("input meleeWeapon", "Enter melee weapon (one of the following constants %s): "),
            arrayOf("input Chapter name", "Enter chapter name: "),
            arrayOf("input Chapter parentLegion", "Enter chapter parent legion: "),
            arrayOf("input Chapter marinesСount", "Enter chapter marines count (%s <= int <= %s): "),

            // Data formats
            arrayOf(
                "Chapter format", "\n" + """
                name: %s
                parent legion: %s
                marines count: %s
            """.trimIndent().prependIndent("\t\t")
            ),
            arrayOf("Coordinates format", "Coordinates (x: %s, y: %s)"),
            arrayOf("LocalDate format", "dd.MM.yyyy"),
            arrayOf(
                "SpaceMarine format", """
                %s (id: %s):
                    coordinates: %s
                    creation date: %s
                    health: %s
                    heart count: %s
                    loyal: %s
                    melee weapon: %s
                    chapter: %s
            """.trimIndent()
            ),
            arrayOf(
                "collection header",
                "id,name,coordinateX,coordinateY,creationDate,health,heartCount,loyal,meleeWeapon,chapterName," +
                        "chapterParentLegion,chapterMarinesCount"
            )
        )
    }

    public override fun getContents(): Array<Array<Any>> {
        return Companion.contents
    }
}