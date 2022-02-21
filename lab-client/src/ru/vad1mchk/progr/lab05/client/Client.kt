package ru.vad1mchk.progr.lab05.client

import ru.vad1mchk.progr.lab05.client.commands.Command
import ru.vad1mchk.progr.lab05.client.commands.CommandReader
import ru.vad1mchk.progr.lab05.client.commands.HistoryStorage
import ru.vad1mchk.progr.lab05.client.exceptions.IllegalFileArgumentException


class Client private constructor() {
    /**
     * Private primary constructor for the client application.
     * Should not be called
     */
    init {
        throw UnsupportedOperationException("This is an utility class and can not be instantiated")
    }

    companion object {
        const val TEST = 20

        /**
         * Main method for the client application
         *
         * @param args Command line arguments
         */
        @Throws(IllegalFileArgumentException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            val reader = CommandReader(System.`in`)
            val history = HistoryStorage()
            var currentCommand: Command?
            if (args.size != 1) {
                throw IllegalFileArgumentException()
            }
            val path = args[0]
            do {
                currentCommand = reader.read()
                if (currentCommand != null) {
                    history.addCommand(currentCommand)
                }
            } while (currentCommand?.commandType != Command.CommandType.EXIT)
            println("End.\nHistory:")
            println(history)
        }
    }
}