package ru.vad1mchk.progr.lab05.client.application

import javafx.event.EventHandler
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import ru.vad1mchk.progr.lab05.client.controllers.SpaceMarineInformationController
import ru.vad1mchk.progr.lab05.client.controllers.SpaceMarineModifierController
import ru.vad1mchk.progr.lab05.client.util.FlatSpaceMarine
import ru.vad1mchk.progr.lab05.client.util.NewStageOpener.Companion.decorateStage
import ru.vad1mchk.progr.lab05.common.application.Drawable

class SpaceMarineModifier(val flatSpaceMarine: FlatSpaceMarine?): Drawable {
    companion object {
        var IS_OPEN = false
    }
    override fun draw() {
        if (IS_OPEN) return
        val loader = FXMLLoader(javaClass.getResource("/SpaceMarineModifierController.fxml"))
        val mainAppRoot: Parent = loader.load()
        val controller = loader.getController<SpaceMarineModifierController>()
        controller.initialize(flatSpaceMarine)
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