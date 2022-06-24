package ru.vad1mchk.progr.lab05.client.application

import javafx.beans.property.ObjectProperty
import javafx.collections.ObservableList
import javafx.event.EventHandler
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import ru.vad1mchk.progr.lab05.client.controllers.CollectionInformationController
import ru.vad1mchk.progr.lab05.client.controllers.UserInformationController
import ru.vad1mchk.progr.lab05.client.util.FlatSpaceMarine
import ru.vad1mchk.progr.lab05.client.util.NewStageOpener.Companion.decorateStage
import ru.vad1mchk.progr.lab05.common.application.Drawable
import java.util.*

class CollectionInformation(val collection: ObjectProperty<ObservableList<FlatSpaceMarine>>): Drawable {
    companion object {
        val IS_OPEN = false
    }

    override fun draw() {
        if (UserInformation.IS_OPEN) return
        val loader = FXMLLoader(javaClass.getResource("/CollectionInformationController.fxml"))
        val mainAppRoot: Parent = loader.load()
        val controller = loader.getController<CollectionInformationController>()
        controller.initialize(collection.get())
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