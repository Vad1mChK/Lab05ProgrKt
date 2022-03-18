package ru.vad1mchk.progr.lab05.client.commands

import ru.vad1mchk.progr.lab05.client.manager.SpaceMarineCollectionManager

class PrintFieldDescendingHealth: Command {
    override fun execute() {
        SpaceMarineCollectionManager.printFieldDescendingHealth()
    }
}