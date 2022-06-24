package ru.vad1mchk.progr.lab05.client.application

import javafx.event.EventHandler
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.stage.Stage
import kotlinx.coroutines.*
import ru.vad1mchk.progr.lab05.client.controllers.LoginFormController
import ru.vad1mchk.progr.lab05.client.controllers.MainApplicationController
import ru.vad1mchk.progr.lab05.client.util.Configuration
import ru.vad1mchk.progr.lab05.client.util.Listener
import ru.vad1mchk.progr.lab05.common.datatypes.User

class MainApplication (private val listener: Listener, private val primaryStage: Stage?) {
    lateinit var mainApplicationController: MainApplicationController
    fun draw() = runBlocking{
        println("")
        val loader = FXMLLoader(javaClass.getResource("/MainApplicationController.fxml"))
        val mainAppRoot: Parent = loader.load()
        mainApplicationController = loader.getController<MainApplicationController>()
        mainApplicationController.initialize()
        mainApplicationController.mainApplicationTableCreateButton.onMouseClicked = EventHandler {
            listener.sendRequest("add", Configuration.user)
            println("111")
        }
        listener.sendRequest("show", Configuration.user)
    }



}