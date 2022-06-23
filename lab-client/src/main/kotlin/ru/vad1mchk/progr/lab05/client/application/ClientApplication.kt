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
import ru.vad1mchk.progr.lab05.client.connection.ClientConnectionHandler
import ru.vad1mchk.progr.lab05.client.controllers.LoginFormController
import ru.vad1mchk.progr.lab05.client.controllers.MainApplicationController
import ru.vad1mchk.progr.lab05.client.strings.StringPropertyManager
import ru.vad1mchk.progr.lab05.client.util.Configuration
import ru.vad1mchk.progr.lab05.client.util.Listener
import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.datatypes.User
import ru.vad1mchk.progr.lab05.common.io.CommandListener
import ru.vad1mchk.progr.lab05.common.io.Printer
import tornadofx.Controller
import java.io.IOException
import java.net.InetAddress
import java.net.SocketException
import java.net.SocketTimeoutException
import kotlinx.coroutines.*

class ClientApplication : Application() {
    var printer: Printer = Printer()
    private var connectionHandler: ClientConnectionHandler = ClientConnectionHandler(printer)
    private lateinit var commandListener: CommandListener
    private lateinit var listener: Listener

    companion object {
        val ICON = Image(ClientApplication::class.java.getResourceAsStream("/icon.png"))
        const val LOGIN_FORM_PREFERRED_WIDTH = 480.0
        const val LOGIN_FORM_PREFERRED_HEIGHT = 640.0
        const val MAIN_APPLICATION_MIN_WIDTH = 960.0
        const val MAIN_APPLICATION_MIN_HEIGHT = 540.0
        const val MAIN_APPLICATION_MAX_WIDTH = 1920.0
        const val MAIN_APPLICATION_MAX_HEIGHT = 1080.0
    }

    override fun start(primaryStage: Stage?) {
        commandListener = CommandListener(System.`in`, false, "1337h4x0r", printer = printer)
        connectionHandler = ClientConnectionHandler(printer)
        //TODO здесь я установил константные значения, нужно будет также сделать это более гибким.
        // Либо забить и убрать вообще выбор данных для подключения, ибо я считаю, что это не нужно.
        // Т.е. фиксированные адрес подключения, порт и т.д. Эти данные не нужно знать пользователю приложения.
        connectionHandler.openConnection(InetAddress.getByName("127.0.0.1"), 1973)
        listener = Listener(connectionHandler, printer)

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
                newStage<MainApplicationController>("/MainApplicationController.fxml")
                    .decorateStage()
                    .apply {
                        minWidth = MAIN_APPLICATION_MIN_WIDTH
                        minHeight = MAIN_APPLICATION_MIN_HEIGHT
                        maxWidth = MAIN_APPLICATION_MAX_WIDTH
                        maxHeight = MAIN_APPLICATION_MAX_HEIGHT
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
                newStage<MainApplicationController>("/MainApplicationController.fxml")
                    .decorateStage()
                    .apply {
                        minWidth = MAIN_APPLICATION_MIN_WIDTH
                        minHeight = MAIN_APPLICATION_MIN_HEIGHT
                        maxWidth = MAIN_APPLICATION_MAX_WIDTH
                        maxHeight = MAIN_APPLICATION_MAX_HEIGHT
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