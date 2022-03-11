package ru.vad1mchk.progr.lab05.client.commands

import ru.vad1mchk.progr.lab05.client.exceptions.ExitProgramException

class Exit : Command {
    override fun execute() {
        throw ExitProgramException()
    }
}