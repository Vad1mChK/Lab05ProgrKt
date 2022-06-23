package ru.vad1mchk.progr.lab05.client.application;

import javafx.event.EventHandler
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage
import javafx.stage.StageStyle
import ru.vad1mchk.progr.lab05.client.controllers.LoginFormController
import ru.vad1mchk.progr.lab05.client.controllers.MainApplicationController
import ru.vad1mchk.progr.lab05.client.strings.StringPropertyManager
import ru.vad1mchk.progr.lab05.client.util.Configuration
import ru.vad1mchk.progr.lab05.client.util.Listener
import ru.vad1mchk.progr.lab05.client.util.NewStageOpener
import ru.vad1mchk.progr.lab05.client.util.NewStageOpener.Companion.decorateStage
import ru.vad1mchk.progr.lab05.common.datatypes.User
import tornadofx.Controller

public class LoginForm (private val listener: Listener, private val primaryStage: Stage?) {
    fun draw() {
        val newStageOpener: NewStageOpener = NewStageOpener()
        val loader = FXMLLoader(javaClass.getResource("/LoginFormController.fxml"))
        val loginFormRoot: Parent = loader.load()
        val loginFormController = loader.getController<LoginFormController>()
        loginFormController.initialize()
        loginFormController.loginFormLoginButton.onMouseClicked = EventHandler {
            val user = User(1,
                loginFormController.loginFormUsernameField.text,
                loginFormController.loginFormPasswordField.text)
            listener.listener("login", user)
            //TODO добавить вызов окна с результатом авторизации
            if (Configuration.user != null)
                newStageOpener.newStage<MainApplicationController>("/MainApplicationController.fxml")
                    .decorateStage()
                    .apply {
                        minWidth = ClientApplication.MAIN_APPLICATION_MIN_WIDTH
                        minHeight = ClientApplication.MAIN_APPLICATION_MIN_HEIGHT
                        maxWidth = ClientApplication.MAIN_APPLICATION_MAX_WIDTH
                        maxHeight = ClientApplication.MAIN_APPLICATION_MAX_HEIGHT
                    }
                    .show()
            // primaryStage?.hide()
        }
        loginFormController.loginFormRegisterButton.onMouseClicked = EventHandler {
            val user = User(1,
                loginFormController.loginFormUsernameField.text,
                loginFormController.loginFormPasswordField.text)
            listener.listener("register", user)
            //TODO добавить вызов окна с результатом регистрации
            if (Configuration.user != null)
                newStageOpener.newStage<MainApplicationController>("/MainApplicationController.fxml")
                    .decorateStage()
                    .apply {
                        minWidth = ClientApplication.MAIN_APPLICATION_MIN_WIDTH
                        minHeight = ClientApplication.MAIN_APPLICATION_MIN_HEIGHT
                        maxWidth = ClientApplication.MAIN_APPLICATION_MAX_WIDTH
                        maxHeight = ClientApplication.MAIN_APPLICATION_MAX_HEIGHT
                    }
                    .show()
            // primaryStage?.hide()
        }
        primaryStage?.apply {
            scene = Scene(loginFormRoot)
            decorateStage()
            isResizable = false
            initStyle(StageStyle.UNIFIED)
            show()
        }
    }
}
