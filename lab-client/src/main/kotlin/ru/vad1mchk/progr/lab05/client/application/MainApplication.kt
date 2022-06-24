package ru.vad1mchk.progr.lab05.client.application

import javafx.beans.property.SimpleBooleanProperty
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
import java.util.EventListener

class MainApplication (private val listener: Listener): Drawable {
    companion object {
        const val MAIN_APPLICATION_MIN_WIDTH = 960.0
        const val MAIN_APPLICATION_MIN_HEIGHT = 540.0
        const val MAIN_APPLICATION_MAX_WIDTH = 1920.0
        const val MAIN_APPLICATION_MAX_HEIGHT = 1080.0
    }
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
        stage.apply {
            minWidth = MAIN_APPLICATION_MIN_WIDTH
            minHeight = MAIN_APPLICATION_MIN_HEIGHT
            maxWidth = MAIN_APPLICATION_MAX_WIDTH
            maxHeight = MAIN_APPLICATION_MAX_HEIGHT
        }
        stage.isResizable = false
        controller.mainApplicationTabPane.selectionModel.selectedItemProperty().addListener {
                observable, oldValue, newValue ->
            if (newValue === controller.mainApplicationMapTab) {
                println("Ресайз заблокирован")
                stage.isResizable = false
            } else {
                println("Ресайз разблокирован")
                stage.isResizable = true
            }
        }
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
        controller.mainApplicationMapPrintFieldDescendingHealthButton.onMouseClicked = EventHandler {
            controller.mainApplicationTableTable.items.sortByDescending { it.health }
        }
        controller.mainApplicationTablePrintFieldDescendingHealthButton.onMouseClicked =
            controller.mainApplicationMapPrintFieldDescendingHealthButton.onMouseClicked
        listener.sendRequest("show", Configuration.user)
    }
}