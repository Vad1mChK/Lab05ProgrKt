package ru.vad1mchk.progr.lab05.client.application;

import javafx.event.EventHandler
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage
import javafx.stage.StageStyle
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import ru.vad1mchk.progr.lab05.client.controllers.LoginFormController
import ru.vad1mchk.progr.lab05.client.controllers.MainApplicationController
import ru.vad1mchk.progr.lab05.client.strings.StringPropertyManager
import ru.vad1mchk.progr.lab05.client.util.Configuration
import ru.vad1mchk.progr.lab05.client.util.Listener
import ru.vad1mchk.progr.lab05.client.util.NewStageOpener
import ru.vad1mchk.progr.lab05.client.util.NewStageOpener.Companion.decorateStage
import ru.vad1mchk.progr.lab05.common.application.Drawable
import ru.vad1mchk.progr.lab05.common.datatypes.User
import tornadofx.Controller
import tornadofx.DrawerStyles

class LoginForm (private val listener: Listener, private val primaryStage: Stage?): Drawable {
    override fun draw() {
        val loader = FXMLLoader(javaClass.getResource("/LoginFormController.fxml"))
        val loginFormRoot: Parent = loader.load()
        val loginFormController = loader.getController<LoginFormController>()
        val mainApplication = MainApplication(listener).also {
            Configuration.mainApplication = it
        }
        loginFormController.initialize()
        loginFormController.loginFormLoginButton.onMouseClicked = EventHandler {
            val user = User(1,
                loginFormController.loginFormUsernameField.text,
                loginFormController.loginFormPasswordField.text)
            listener.sendRequest("login", user)
            //TODO тут хорошо бы PopUp с загрузочкой :)
            Thread.sleep(1000L)
            //TODO добавить вызов окна с результатом авторизации
            if (Configuration.user != null) {
                mainApplication.draw()
                primaryStage?.hide()
            } else {
                //TODO авторизация не выполнена и что тогда

            }
        }
        loginFormController.loginFormRegisterButton.onMouseClicked = EventHandler {
            val user = User(
                1,
                loginFormController.loginFormUsernameField.text,
                loginFormController.loginFormPasswordField.text
            )
            listener.sendRequest("register", user)
            //TODO тут хорошо бы PopUp с загрузочкой :)
            Thread.sleep(3000L)
            //TODO добавить вызов окна с результатом регистрации
            if (Configuration.user != null) {
                mainApplication.draw()
                primaryStage?.hide()
            } else {
                //TODO регистрация не выполнена и что тогда
            }
        }
        primaryStage?.apply {
            scene = Scene(loginFormRoot)
            decorateStage()
            isResizable = false
            //initStyle(StageStyle.UNIFIED)
            show()
        }
    }
}
