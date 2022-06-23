package ru.vad1mchk.progr.lab05.client.util

import ru.vad1mchk.progr.lab05.client.connection.ClientConnectionHandler
import ru.vad1mchk.progr.lab05.client.controllers.LoginFormController
import ru.vad1mchk.progr.lab05.common.communication.EnteredCommand
import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.RequestCreator
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.datatypes.User
import ru.vad1mchk.progr.lab05.common.io.Printer
import java.io.IOException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.util.*

/*
    Тут вынес слушатель в отдельный класс, чтобы позже можно было использовать его в отдельном потоке
 */
class Listener (private val connectionHandler: ClientConnectionHandler, private val printer: Printer) {
    private val requestCreator = RequestCreator(Configuration.user, Scanner(System.`in`), printer)


    fun listener(commandName: String, user: User?) {
        listen(EnteredCommand.fromString(commandName)?.let { it ->
            requestCreator.requestFromEnteredCommand(it,
                user = user
            )?.also {
                Configuration.user?.let { user -> it.user = user }
                it.isLoggedInRequest = (Configuration.user != null)
            }
        })?.also {
            println(it.stringMessage)
            it.spaceMarines?.stream()?.forEach { marine -> printer.printNewLine(marine.toString()) }
            if (it.user != null) {
                Configuration.user = it.user
            }
        }

    }

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
}