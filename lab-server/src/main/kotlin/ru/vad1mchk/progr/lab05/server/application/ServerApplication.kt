package ru.vad1mchk.progr.lab05.server.application

import org.postgresql.util.PSQLException
import ru.vad1mchk.progr.lab05.common.application.AbstractApplication
import ru.vad1mchk.progr.lab05.common.exceptions.InvalidDataException
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.server.collection.SpaceMarineCollectionManager
import ru.vad1mchk.progr.lab05.server.commander.CommandInvoker
import ru.vad1mchk.progr.lab05.server.connection.ServerConnectionHandler
import ru.vad1mchk.progr.lab05.server.database.DatabaseConnector
import ru.vad1mchk.progr.lab05.server.database.DatabaseInitializer
import ru.vad1mchk.progr.lab05.server.database.DatabaseNegotiator
import ru.vad1mchk.progr.lab05.server.security.Sha1PasswordHasher
import ru.vad1mchk.progr.lab05.server.util.TerminalListenerThread
import java.io.Console
import java.io.IOException

/**
 * Implementation of [AbstractApplication] used solely by the server.
 */
class ServerApplication : AbstractApplication() {
    lateinit var connectionHandler: ServerConnectionHandler
    override var console: Console = System.console()
    override var printer = Printer()
    private lateinit var connector: DatabaseConnector
    private val collectionManager = SpaceMarineCollectionManager()
    private lateinit var commandInvoker: CommandInvoker
    private lateinit var negotiator: DatabaseNegotiator
    private val hasher = Sha1PasswordHasher()

    override fun launch(args: Array<String>) {
        printer.printNewLine("Сервер менеджера космических десантников приветствует вас.")
        printer.printNewLine("Чтобы войти в базу данных, введите имя хоста, имя базы данных, логин и пароль.")
        setDatabaseVariables()
        connector = DatabaseConnector()
        DatabaseInitializer(connector).initialize()
        negotiator = DatabaseNegotiator(hasher, connector)
        negotiator.selectAllSpaceMarines().toCollection(collectionManager.collection())
        commandInvoker = CommandInvoker(printer, collectionManager, negotiator)
        printer.printNewLine("Подключение к базе данных установлено.")
        try {
            connectionHandler = ServerConnectionHandler(readPort(), printer, commandInvoker)
            TerminalListenerThread(commandInvoker, printer).start()
            connectionHandler.run()
        } catch (e: IOException) {
            printer.printError("Во время открытия порта произошла ошибка ввода-вывода. Возможно, порт уже занят.")
        }
    }

    private fun readDatabaseHostName(): String {
        val defaultValue = "localhost"
        printer.printNoNewLine(
            "Введите имя хоста базы данных (оставьте пустым для значения по умолчанию $defaultValue): "
        )
        return console.readLine().ifBlank { defaultValue }
    }

    private fun readDatabaseName(): String {
        while (true) {
            printer.printNoNewLine("Введите имя базы данных: ")
            try {
                return console.readLine().ifEmpty {
                    throw InvalidDataException("Имя базы данных не может быть пустым.")
                }
            } catch (e: InvalidDataException) {
                printer.printError(e)
            }
        }
    }

    private fun readDatabaseUserName(): String {
        while (true) {
            printer.printNoNewLine("Введите имя пользователя базы данных: ")
            try {
                return console.readLine().ifEmpty {
                    throw InvalidDataException("Имя пользователя базы данных не может быть пустым.")
                }
            } catch (e: InvalidDataException) {
                printer.printError(e)
            }
        }
    }

    private fun readDatabasePassword(): String {
        printer.printNoNewLine("Введите пароль пользователя базы данных: ")
        return console.readPassword().concatToString()
    }

    private fun setDatabaseVariables() {
        while (true) {
            try {
                DatabaseConnector.setVariables(
                    readDatabaseHostName(),
                    readDatabaseName(),
                    readDatabaseUserName(),
                    readDatabasePassword()
                )
                return
            } catch (e: PSQLException) {
                printer.printError("Данные для входа в базу данных введены неверно. Пожалуйста, повторите ввод.")
            }
        }
    }
}