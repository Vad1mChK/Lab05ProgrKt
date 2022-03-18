package ru.vad1mchk.progr.lab05.client.commands

import ru.vad1mchk.progr.lab05.client.manager.SpaceMarineCollectionManager
import ru.vad1mchk.progr.lab05.client.messages.Messages

/**
 * Command that prints out information about the collection,
 * such as:
 * collection type,
 * elements type,
 * collection size,
 * initialization date
 */
class Info : Command {
    override fun execute() {
        Messages.say(Messages.Level.INFO, SpaceMarineCollectionManager.info())
    }
}