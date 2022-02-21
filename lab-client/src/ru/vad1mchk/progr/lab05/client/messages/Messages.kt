package ru.vad1mchk.progr.lab05.client.messages

import java.util.*

class Messages private constructor(): ResourceBundle(){
    companion object {
        var contents = hashMapOf<String, String>(
            "badgeError" to "["+ Char(27) +"[31;2mError"+ Char(27)+"[0m] ",
            "badgeWarning" to "["+Char(27)+"[33;2mWarning"+Char(27)+"[0m] ",
            "badgeInfo" to "["+Char(27)+"[34;2mInfo"+Char(27)+"[0m] ",
            "helpString" to
                    """
                    help
                        Print help for available commands
                    info
                        Print information about collection as follows: type, initialization date, element count
                    show
                        Print all elements of the collection in string representation
                    add {element}
                        Add a new element to the collection
                    update id {element}
                        Update the element with the specified ID in collection
                    remove_by_id id
                        Remove the element with the specified ID from collection
                    clear
                        Clear collection
                    save 
                        Save collection into file
                    execute_script file_name
                        Execute the script from a file
                    exit
                        End program without saving
                    add_if_min {element}
                        Add a new element to the collection if it is less that the rest
                    remove_greater {element}
                        Remove all elements from the collection if they are bigger than the following element
                    history
                        Print the names of last 12 commands executed
                    filter_less_than_melee_weapon meleeWeapon
                        Print all elements with meleeWeapon field less than the specified value 
                    filter_greater_than_heart_count heartCount
                        Print all elements with heartCount field greater than the specified value 
                    print_field_descending_health
                        Print all health values of elements in descending order
                    Any string starting with '#' (hash) symbol is treated as a comment
                    """.trimIndent().trimStart('\n'),
            "infoFormatString" to
                    """
                    Collection type: %s
                    Initialization date: %s
                    Elements count: %d
                    Occupied IDs: %s
                    """.trimIndent().trimStart('\n'),
            "inputAsk" to "\$ ",
            "emptyProgramArgumentException" to "Collection file must be specified.",
            "invalidProgramArgumentExceptionMoreThanOne" to "Exactly one argument must be passed.",
            "invalidProgramArgumentExceptionNotCSV" to "Collection file must be in *.csv format.",
            "accessFileException" to "File with this name cannot be found or accessed.",
            "invalidCommandNameException" to "No command with name \"%s\" found.",
            "invalidCommandArgumentExceptionMissingArg" to "Command \"%s\" must have an argument.",
            "invalidCommandArgumentExceptionUnwantedArgs" to "Command \"%s\" must not have arguments.",
            "invalidCommandArgumentExceptionMoreThanOne" to "Command \"%s\" can only have one argument.",
            "invalidCommandArgumentExceptionIllegalDatatype" to "Argument of command \"%s\" must be of type %s."
            ).toMutableMap()
        fun get(key: String): String {
            if (contents.containsKey(key)) {
                return contents[key] as String
            }
            throw InternalError("Cannot find message string of key $key.")
        }
    }
    override fun handleGetObject(key: String): Any? {
        if (contents.containsKey(key)) {
            return contents[key]
        }
        return null
    }
    override fun getKeys(): Enumeration<String> {
        return Hashtable(contents).elements()
    }
}