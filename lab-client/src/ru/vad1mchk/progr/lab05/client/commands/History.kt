package ru.vad1mchk.progr.lab05.client.commands

import ru.vad1mchk.progr.lab05.client.terminal.HistoryStorage

class History: Command {
    override fun execute() {
        HistoryStorage.print()
    }
}