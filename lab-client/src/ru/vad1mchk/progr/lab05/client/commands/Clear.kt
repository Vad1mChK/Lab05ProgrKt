package ru.vad1mchk.progr.lab05.client.commands

import ru.vad1mchk.progr.lab05.client.manager.SpaceMarineCollectionManager

class Clear: Command {
    override fun execute() {
        SpaceMarineCollectionManager.clear()
    }
}