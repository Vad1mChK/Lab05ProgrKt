package ru.vad1mchk.progr.lab05.client.commands

import ru.vad1mchk.progr.lab05.client.messages.Messages

class Help : Command {
    override fun execute() {
        println(Messages["helpString"])
    }
}