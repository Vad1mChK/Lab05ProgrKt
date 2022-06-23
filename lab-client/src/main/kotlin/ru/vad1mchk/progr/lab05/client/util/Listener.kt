package ru.vad1mchk.progr.lab05.client.util

import javafx.scene.control.TableView
import kotlinx.coroutines.runBlocking
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
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import ru.vad1mchk.progr.lab05.client.controllers.MainApplicationController

/*
    Тут вынес слушатель в отдельный класс, чтобы позже можно было использовать его в отдельном потоке
 */
class Listener (private val connectionHandler: ClientConnectionHandler, private val printer: Printer) {
    private val requestCreator = RequestCreator(Configuration.user, Scanner(System.`in`), printer)
    private val mainApplicationController = MainApplicationController()

    fun listener(commandName: String, user: User?) {
        listen(EnteredCommand.fromString(commandName)?.let { it ->
            requestCreator.requestFromEnteredCommand(
                it,
                user = user
            )?.also {
                Configuration.user?.let { user -> it.user = user }
                it.isLoggedInRequest = (Configuration.user != null)
            }
        })?.also {
            //TODO В качестве получаемого ответа с сервера мне кажется имеет смысл ВСЕГДА получать коллекцию,
            // чтобы обновлять значения в таблице, а для строковых ответов можно сделать отдельное окно по типу
            // логгера или терминала. Но в любом случае нужно заменить вывод в консоль
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

    fun listenChanges() {
        while(true) {
            if (!connectionHandler.isOpen) {
                connectionHandler.reopenConnection()
            }
            if (connectionHandler.isOpen) {
                while (true) {
                    try {
                        val response = connectionHandler.receive(connectionHandler.socket.receiveBufferSize)
                        if (response?.notification == true && response.spaceMarines != null) {
                            mainApplicationController.mainApplicationTableTable.items.clear()
                            val newSpaceMarines: LinkedList<FlatSpaceMarine> = LinkedList()
                            response.spaceMarines?.forEach { newSpaceMarines.add(FlatSpaceMarine.fromSpaceMarine(it)) }
                            mainApplicationController.mainApplicationTableTable.items.setAll(newSpaceMarines)
                            //TODO тут должен быть метод для создания уведомления о обновлении таблички другим юзером
                        }
                    } catch (e: SocketTimeoutException) {
                        printer.printError("Время ожидания ответа от сервера истекло.")
                        break
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