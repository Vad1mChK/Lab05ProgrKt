package ru.vad1mchk.progr.lab05.client.application

import javafx.event.EventHandler
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import kotlinx.coroutines.*
import ru.vad1mchk.progr.lab05.client.controllers.MainApplicationController
import ru.vad1mchk.progr.lab05.client.util.Configuration
import ru.vad1mchk.progr.lab05.client.util.Listener
import ru.vad1mchk.progr.lab05.client.util.NewStageOpener.Companion.decorateStage
import ru.vad1mchk.progr.lab05.common.application.Drawable
import tornadofx.Stylesheet.Companion.root

class MainApplication (private val listener: Listener): Drawable {
    lateinit var controller: MainApplicationController
        private set
    var isDrawn = false
        private set
    override fun draw() = runBlocking{
        val loader = FXMLLoader(javaClass.getResource("/MainApplicationController.fxml"))
        val mainAppRoot: Parent = loader.load()
        controller = loader.getController<MainApplicationController>()
        controller.initialize()
        val stage = Stage()
        stage.scene = Scene(mainAppRoot)
        stage.decorateStage()
        stage.show()
        isDrawn = true
        controller.mainApplicationTableCreateButton.onMouseClicked = EventHandler {
            listener.sendRequest("add", Configuration.user)
            println("111")
        }
        controller.mainApplicationMapInfoButton.onMouseClicked = EventHandler {
            controller.collectionInformation.draw()
        }
        controller.mainApplicationTableInfoButton.onMouseClicked = controller.mainApplicationMapInfoButton.onMouseClicked
        listener.sendRequest("show", Configuration.user)
    }
}