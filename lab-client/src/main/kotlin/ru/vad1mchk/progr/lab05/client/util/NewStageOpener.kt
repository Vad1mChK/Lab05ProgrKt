package ru.vad1mchk.progr.lab05.client.util;

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.PopupWindow
import javafx.stage.Stage
import ru.vad1mchk.progr.lab05.client.application.ClientApplication
import ru.vad1mchk.progr.lab05.client.strings.StringPropertyManager
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

    inline fun<reified T: Controller> newPopupWindow(fxmlLocation: String): PopupWindow {
        TODO()
    }

    companion object {
        @JvmStatic
         fun Stage.decorateStage(
            cssLocation: String = "/synthwave.css",
            icon: Image = ClientApplication.ICON,
            titleStringPropertyKey: String = "applicationName"
         ): Stage {
            try {
                this.titleProperty().bind(StringPropertyManager.createBinding(titleStringPropertyKey))
                this.scene.stylesheets.add(ClientApplication::class.java.getResource(cssLocation)?.toExternalForm())
                this.icons.setAll(icon)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return this
        }
    }
}
