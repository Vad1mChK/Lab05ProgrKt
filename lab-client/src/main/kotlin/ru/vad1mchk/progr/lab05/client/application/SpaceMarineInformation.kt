package ru.vad1mchk.progr.lab05.client.application

import javafx.event.EventHandler
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import ru.vad1mchk.progr.lab05.client.controllers.SpaceMarineInformationController
import ru.vad1mchk.progr.lab05.client.util.FlatSpaceMarine
import ru.vad1mchk.progr.lab05.client.util.NewStageOpener.Companion.decorateStage
import ru.vad1mchk.progr.lab05.common.application.Drawable

class SpaceMarineInformation(private val flatSpaceMarine: FlatSpaceMarine): Drawable {
    private val spaceMarine = flatSpaceMarine.toSpaceMarine()
    companion object {
        val IS_OPEN = false
    }
    override fun draw() {
        if (UserInformation.IS_OPEN) return
        val loader = FXMLLoader(javaClass.getResource("/SpaceMarineInformationController.fxml"))
        val mainAppRoot: Parent = loader.load()
        val controller = loader.getController<SpaceMarineInformationController>()
        controller.initialize(spaceMarine)
        val stage = Stage()
        stage.scene = Scene(mainAppRoot)
        stage.isResizable = false
        stage.decorateStage()
        UserInformation.IS_OPEN = true
        stage.show()
        stage.onCloseRequest = EventHandler {
            UserInformation.IS_OPEN = false
            stage.close()
        }
        stage.onHidden = EventHandler {
            UserInformation.IS_OPEN = false
        }
    }
}