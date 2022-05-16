package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.connection.Request
import ru.vad1mchk.progr.lab05.common.connection.Response
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.common.messages.Message
import ru.vad1mchk.progr.lab05.server.util.Configuration
import java.util.*

/**
 * A command with a [MeleeWeapon] argument that does the following:
 *
 * Print out all elements with the melee weapon less than this.
 */
class FilterLessThanMeleeWeaponCommand: Command(
    "filter_less_than_melee_weapon",
    "MELEE_WEAPON: Print out all elements with the melee weapon less than this.",
    false
) {
    override fun invoke(request: Request): Any? {
        val collectionToShow = Configuration.COLLECTION_MANAGER.collection()
        return Response(
            Message(Message.MessageType.INFO, Configuration.STRING_RESOURCES.getString("show success")),
            collectionToShow.sorted() as LinkedList<SpaceMarine>
        )
    }
}