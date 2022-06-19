package ru.vad1mchk.progr.lab05.client.application

import ru.vad1mchk.progr.lab05.client.connection.ClientConnectionHandler
import ru.vad1mchk.progr.lab05.client.util.Configuration
import ru.vad1mchk.progr.lab05.common.application.AbstractApplication
import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.RequestCreator
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.exceptions.FileException
import ru.vad1mchk.progr.lab05.common.io.CommandListener
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.common.io.ScriptFileReader
import java.io.Console
import java.io.IOException
import java.net.InetAddress
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.*

/**
 * Implementation of [AbstractApplication] used solely by client.
 */
class ClientApplicationOld : AbstractApplication() {
    private val connectionHandler: ClientConnectionHandler
    private var commandListener: CommandListener
    override var console: Console = System.console()
    override var printer: Printer = Printer()
    private val requestCreator = RequestCreator(Configuration.user, Scanner(System.`in`), printer)

    init {
        commandListener = CommandListener(System.`in`, false, "1337h4x0r", printer = printer)
        connectionHandler = ClientConnectionHandler(printer)
    }


    override fun launch(args: Array<String>) {
        printer.printNewLine(
            """
            |Клиент менеджера космических десантников приветствует вас.
            |Данное приложение работает только при наличии соединения с сервером.
            |Для продолжения введите адрес и порт сервера в локальной сети. 
            """.trimMargin()
        )
        connectionHandler.openConnection(readInetAddress(), readPort())
        mainLoop()
    }

    /**
     * Main loop of the program, in which the command requests are sent and responses received.
     */
    private fun mainLoop() {
        while (true) {
            val enteredCommand = commandListener.readCommand()
            if (enteredCommand != null) {
                if ("exit" == enteredCommand.name.lowercase()) {
                    if (enteredCommand.arguments.isEmpty()) {
                        printer.printNewLine("Завершение работы.")
                        break
                    } else {
                        printer.printError("Для того чтобы выйти из приложения, введите команду exit без аргументов.")
                    }
                } else {
                    if ("execute_script" == enteredCommand.name.lowercase() && Configuration.user != null) {
                        if (enteredCommand.arguments.size == 1) {
                            executeScript(enteredCommand.arguments[0])
                        } else {
                            printer.printError("Неверное количество аргументов команды.")
                        }
                    }
                    listen(requestCreator.requestFromEnteredCommand(enteredCommand)?.also {
                        Configuration.user?.let { user -> it.user = user }
                        it.isLoggedInRequest = (Configuration.user != null)
                    })?.also {
                        println(it.stringMessage)
                        it.spaceMarines?.stream()?.forEach { marine -> printer.printNewLine(marine.toString()) }
                        if (it.user != null) {
                            Configuration.user = it.user
                        }
                    }
                }

            }
        }
    }

    /**
     * Reads IP address of the server from the standard input, looping until a valid address that can be found is
     * entered.
     * @return The entered IP address.
     */
    private fun readInetAddress(): InetAddress {
        while (true) {
            printer.printNoNewLine("Введите IP-адрес сервера: ")
            try {
                return InetAddress.getByName(console.readLine())
            } catch (e: UnknownHostException) {
                printer.printError("Адрес не найден в сети.")
            }
        }
    }

    /**
     * Listens to the connection, receiving incoming responses from the server.
     * @param request Request to send.
     * @return The server's response to the sent request.
     */
    private fun listen(request: Request?): Response? {
        if (!connectionHandler.isOpen) {
            connectionHandler.reopenConnection()
        }
        if (connectionHandler.isOpen) {
            try {
                if (request == null) return null
                connectionHandler.send(request)
                return connectionHandler.receive(connectionHandler.socket.receiveBufferSize)
            } catch (e: SocketTimeoutException) {
                printer.printError("Время ожидания ответа от сервера истекло.")
            } catch (e: SocketException) {
                printer.printError("Соединение с сервером было разорвано.")
                connectionHandler.close()
            } catch (e: IOException) {
                printer.printError("Во время обмена информацией с сервером произошла ошибка ввода-вывода.")
                connectionHandler.close()
            }
        }
        return null
    }

    /**
     * Executes the specified script file, sending commands to the server one by one.
     * @param filePath Path of script file to execute.
     */
    private fun executeScript(filePath: String) {
        try {
            val scriptFileReader = ScriptFileReader(filePath, Configuration.user, printer)
            scriptFileReader.readAll().forEach { connectionHandler.send(it) }
        } catch (e: FileException) {
            printer.printError(e)
        }
    }
}