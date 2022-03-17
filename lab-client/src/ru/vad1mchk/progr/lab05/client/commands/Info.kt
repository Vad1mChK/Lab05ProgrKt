package ru.vad1mchk.progr.lab05.client.commands

import ru.vad1mchk.progr.lab05.client.manager.SpaceMarineCollectionManager
import ru.vad1mchk.progr.lab05.client.messages.Messages

class Info : Command {
    override fun execute() {
        Messages.say(Messages.Level.INFO, SpaceMarineCollectionManager.info())
    }
}