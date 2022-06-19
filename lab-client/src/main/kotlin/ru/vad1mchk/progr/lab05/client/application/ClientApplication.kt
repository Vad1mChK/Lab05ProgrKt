package ru.vad1mchk.progr.lab05.client.application

import javafx.application.Application
import javafx.event.EventHandler
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.text.Font
import javafx.stage.Stage
import javafx.stage.StageStyle
import ru.vad1mchk.progr.lab05.client.controllers.LoginFormController
import ru.vad1mchk.progr.lab05.client.controllers.MainApplicationController
import ru.vad1mchk.progr.lab05.client.strings.StringPropertyManager
import tornadofx.Controller

class ClientApplication : Application() {
    companion object {
        val ICON = Image(ClientApplication::class.java.getResourceAsStream("/icon.png"))
        const val LOGIN_FORM_PREFERRED_WIDTH = 480.0
        const val LOGIN_FORM_PREFERRED_HEIGHT = 640.0
    }

    override fun start(primaryStage: Stage?) {
        val loader = FXMLLoader(javaClass.getResource("/LoginFormController.fxml"))
        val loginFormRoot: Parent = loader.load()
        val loginFormController = loader.getController<LoginFormController>()
        loginFormController.initialize()
        loginFormController.loginFormLoginButton.onMouseClicked = EventHandler {
            newStage<MainApplicationController>("/MainApplicationController.fxml").decorateStage().show()
            primaryStage?.hide()
        }
        primaryStage?.apply {
            scene = Scene(loginFormRoot)
            decorateStage()
            isResizable = false
            initStyle(StageStyle.UNIFIED)
            show()
        }
    }

    private inline fun<reified T: Controller> newStage(fxmlLocation: String): Stage {
        val loader = FXMLLoader(javaClass.getResource(fxmlLocation))
        val root: Parent = loader.load()
        val controller = loader.getController<T>()
        val stage = Stage()
        stage.scene = Scene(root)
        return stage
    }

    private fun Stage.decorateStage(
        cssLocation: String = "/synthwave.css",
        icon: Image = ICON,
        titleStringPropertyKey: String = "applicationName"
    ): Stage {
        try {
            this.titleProperty().bind(StringPropertyManager.createBinding(titleStringPropertyKey))
            this.scene.stylesheets.add(ClientApplication::class.java.getResource(cssLocation)?.toExternalForm())
            this.icons.add(icon)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return this
    }
}