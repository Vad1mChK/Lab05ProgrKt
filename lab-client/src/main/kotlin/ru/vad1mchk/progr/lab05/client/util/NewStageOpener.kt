package ru.vad1mchk.progr.lab05.client.util;

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import tornadofx.Controller

class NewStageOpener {
    inline fun<reified T: Controller> newStage(fxmlLocation: String): Stage {
        val loader = FXMLLoader(javaClass.getResource(fxmlLocation))
        val root: Parent = loader.load()
        val controller = loader.getController<T>()
        val stage = Stage()
        stage.scene = Scene(root)
        return stage
    }
}
