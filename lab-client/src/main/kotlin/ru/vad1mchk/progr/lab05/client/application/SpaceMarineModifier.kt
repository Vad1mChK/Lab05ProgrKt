package ru.vad1mchk.progr.lab05.client.application

import javafx.event.EventHandler
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.vad1mchk.progr.lab05.client.controllers.SpaceMarineInformationController
import ru.vad1mchk.progr.lab05.client.controllers.SpaceMarineModifierController
import ru.vad1mchk.progr.lab05.client.strings.StringPropertyManager
import ru.vad1mchk.progr.lab05.client.util.Configuration
import ru.vad1mchk.progr.lab05.client.util.FlatSpaceMarine
import ru.vad1mchk.progr.lab05.client.util.Listener
import ru.vad1mchk.progr.lab05.client.util.NewStageOpener.Companion.decorateStage
import ru.vad1mchk.progr.lab05.common.application.Drawable
import tornadofx.*
import java.time.LocalDate

class SpaceMarineModifier(val flatSpaceMarine: FlatSpaceMarine?, private val listener: Listener): Drawable {
    companion object {
        var IS_OPEN = false
    }
    @OptIn(DelicateCoroutinesApi::class)
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
        controller.spaceMarineModifierSubmitButton.onMouseClicked = EventHandler {
            if (flatSpaceMarine == null) {
                FlatSpaceMarine(1, Configuration.user?.userName, controller.spaceMarineModifierNameField.text,
                    StringPropertyManager.integerFormat.parse(controller.spaceMarineModifierCoordinatesXField.text).toInt(),
                    StringPropertyManager.numberFormat.parse(controller.spaceMarineModifierCoordinatesYField.text).toFloat(),
                    LocalDate.now(),
                    StringPropertyManager.numberFormat.parse(controller.spaceMarineModifierHealthField.text).toDouble(),
                    StringPropertyManager.integerFormat.parse(controller.spaceMarineHeartCountSlider.value.toString()).toLong(),
                    controller.spaceMarineModifierLoyalBox.isSelected,
                    StringPropertyManager
                    )
                GlobalScope.launch { listener.sendRequest("add", Configuration.user) }
        }
        }
    }
}