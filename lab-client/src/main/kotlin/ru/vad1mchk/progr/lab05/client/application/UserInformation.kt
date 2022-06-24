package ru.vad1mchk.progr.lab05.client.application

import javafx.event.EventHandler
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import ru.vad1mchk.progr.lab05.client.controllers.UserInformationController
import ru.vad1mchk.progr.lab05.client.util.NewStageOpener.Companion.decorateStage
import ru.vad1mchk.progr.lab05.common.application.Drawable

class UserInformation(private val userName: String?, private val userMarinesCount: Int = 0): Drawable {
    companion object {
        var IS_OPEN = false
    }
    override fun draw() {
        if (IS_OPEN) return
        val loader = FXMLLoader(javaClass.getResource("/UserInformationController.fxml"))
        val mainAppRoot: Parent = loader.load()
        val controller = loader.getController<UserInformationController>()
        controller.initialize(userName, userMarinesCount)
        val stage = Stage()
        stage.scene = Scene(mainAppRoot)
        stage.isResizable = false
        stage.decorateStage()
        IS_OPEN = true
        stage.show()
        stage.onCloseRequest = EventHandler {
            IS_OPEN = false
            stage.close()
        }
        stage.onHidden = EventHandler {
            IS_OPEN = false
        }
    }
}