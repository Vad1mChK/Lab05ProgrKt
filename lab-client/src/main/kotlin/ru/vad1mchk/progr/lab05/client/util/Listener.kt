package ru.vad1mchk.progr.lab05.client.util

import ru.vad1mchk.progr.lab05.client.application.MainApplication
import ru.vad1mchk.progr.lab05.client.connection.ClientConnectionHandler
import ru.vad1mchk.progr.lab05.common.communication.EnteredCommand
import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.RequestCreator
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

    fun sendRequest(commandName: String, user: User?) {
        send(EnteredCommand.fromString(commandName)?.let { it ->
            requestCreator.requestFromEnteredCommand(
                it,
                user = user
            )?.also {
                Configuration.user?.let { user -> it.user = user }
                it.isLoggedInRequest = (Configuration.user != null)
            }
        })
    }

    private fun send(request: Request?) {
        if (!connectionHandler.isOpen) {
            connectionHandler.reopenConnection()
        }
        if (connectionHandler.isOpen) {
            try {
                if (request == null) return
                connectionHandler.send(request)
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
    }

    fun listenChanges() {
        while(true) {
            if (!connectionHandler.isOpen) {
                connectionHandler.reopenConnection()
            }
            if (connectionHandler.isOpen) {
                while (true) {
                    try {
                        val response = connectionHandler.receive(connectionHandler.socket.receiveBufferSize)
                        if (response?.notification == true) {
                            val newSpaceMarines: LinkedList<FlatSpaceMarine> = LinkedList()
                            response.spaceMarines?.forEach { newSpaceMarines.add(FlatSpaceMarine.fromSpaceMarine(it)) }
                            //TODO Вадииииим, как динамически обновлять таблицу? Нужен метод для этого по типу updateTable(newSpaceMarines)
                            //TODO тут должен быть метод для создания уведомления о обновлении таблички другим юзером
                        } else if (response != null){
                            println(response.stringMessage)
                            response.spaceMarines?.stream()?.forEach { marine -> printer.printNewLine(marine.toString()) }
                            if (response.user != null) {
                                Configuration.user = response.user
                            }
                        }
                    } catch (e: SocketTimeoutException) {
                        continue
                    } catch (e: SocketException) {
                        printer.printError("Соединение с сервером было разорвано.")
                        connectionHandler.close()
                        break
                    } catch (e: IOException) {
                        printer.printError("Во время обмена информацией с сервером произошла ошибка ввода-вывода.")
                        connectionHandler.close()
                        break
                    }
                }
            }
        }
    }
}