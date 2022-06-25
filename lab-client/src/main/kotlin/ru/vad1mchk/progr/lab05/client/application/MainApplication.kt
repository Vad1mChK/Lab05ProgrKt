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
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import javafx.scene.paint.Color;
import ru.vad1mchk.progr.lab05.client.util.FlatSpaceMarine
import tornadofx.*
import java.awt.event.MouseEvent
import kotlin.collections.sortByDescending
import kotlin.io.path.createTempDirectory

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

    @OptIn(DelicateCoroutinesApi::class)
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
        listener.sendRequest("show", Configuration.user)
        controller.mainApplicationMapInfoButton.onMouseClicked = EventHandler {
            controller.collectionInformation.draw()
        }
        controller.mainApplicationTableInfoButton.onMouseClicked = controller.mainApplicationMapInfoButton.onMouseClicked

        controller.mainApplicationMapPrintFieldDescendingHealthButton.onMouseClicked = EventHandler {
            controller.mainApplicationTableTable.items.sortByDescending { it.health }
        }
        controller.mainApplicationTablePrintFieldDescendingHealthButton.onMouseClicked =
            controller.mainApplicationMapPrintFieldDescendingHealthButton.onMouseClicked
        controller.mainApplicationTableCreateButton.onMouseClicked = EventHandler {
            //...
            SpaceMarineModifier(null, listener).draw()
            //...
        }
        controller.mainApplicationMapCreateButton.onMouseClicked =
            controller.mainApplicationTableCreateButton.onMouseClicked
        controller.mainApplicationTableAddIfMinButton.onMouseClicked = EventHandler {
            GlobalScope.launch { listener.sendRequest("add_if_min", Configuration.user) }
        }
        controller.mainApplicationMapAddIfMinButton.onMouseClicked =
            controller.mainApplicationTableAddIfMinButton.onMouseClicked
        controller.mainApplicationMapUpdateButton.onMouseClicked = EventHandler {
            SpaceMarineModifier(controller.mainApplicationTableTable.selectedItem, listener).draw()
            GlobalScope.launch { listener.sendRequest("update", Configuration.user) }
        }
        controller.mainApplicationTableUpdateButton.onMouseClicked =
            controller.mainApplicationMapUpdateButton.onMouseClicked
        controller.mainApplicationMapDeleteButton.onMouseClicked = EventHandler {
            GlobalScope.launch { listener.sendRequest("remove_by_id", Configuration.user) }
        }
        controller.mainApplicationTableDeleteButton.onMouseClicked =
            controller.mainApplicationMapDeleteButton.onMouseClicked
        controller.mainApplicationMapClearButton.onMouseClicked = EventHandler {
            GlobalScope.launch { listener.sendRequest("clear", Configuration.user) }
        }
        controller.mainApplicationTableClearButton.onMouseClicked =
            controller.mainApplicationMapClearButton.onMouseClicked
        controller.mainApplicationMapDeleteGreaterButton.onMouseClicked = EventHandler {
            GlobalScope.launch { listener.sendRequest("remove_greater", Configuration.user) }
        }
        controller.mainApplicationTableDeleteGreaterButton.onMouseClicked =
            controller.mainApplicationMapDeleteGreaterButton.onMouseClicked
        controller.mainApplicationMapHistoryButton.onMouseClicked = EventHandler {
            GlobalScope.launch { listener.sendRequest("history", Configuration.user) }
        }
        controller.mainApplicationTableHistoryButton.onMouseClicked =
            controller.mainApplicationMapHistoryButton.onMouseClicked
        controller.mainApplicationMapExecuteScriptButton.onMouseClicked = EventHandler {
            GlobalScope.launch { listener.sendRequest("execute_script", Configuration.user) }
        }
        controller.mainApplicationTableExecuteScriptButton.onMouseClicked =
            controller.mainApplicationMapExecuteScriptButton.onMouseClicked
    }
}