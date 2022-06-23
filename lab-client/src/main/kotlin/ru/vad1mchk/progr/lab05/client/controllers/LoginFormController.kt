package ru.vad1mchk.progr.lab05.client.controllers

import javafx.collections.FXCollections
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.input.KeyCode
import javafx.scene.layout.*
import javafx.scene.text.Font
import ru.vad1mchk.progr.lab05.client.strings.StringPropertyManager
import ru.vad1mchk.progr.lab05.client.util.LocaleConverter
import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.datatypes.User
import tornadofx.Controller
import java.util.Locale

class LoginFormController: Controller() {
    @FXML
    lateinit var loginFormLanguageChoice: ChoiceBox<Locale>
    @FXML
    lateinit var loginFormLanguageLabel: Label
    @FXML
    lateinit var loginFormRegisterButton: Button
    @FXML
    lateinit var loginFormLoginButton: Button
    @FXML
    lateinit var loginFormPasswordField: PasswordField
    @FXML
    lateinit var loginFormPasswordLabel: Label
    @FXML
    lateinit var loginFormUsernameField: TextField
    @FXML
    lateinit var loginFormUsernameLabel: Label
    @FXML
    lateinit var loginFormWelcomeLabel: Label
    @FXML
    lateinit var loginFormBackground: VBox

    fun initialize() {
        loginFormBackground.styleClass.add("background")
        loginFormWelcomeLabel.textProperty().bind(StringPropertyManager.createBinding("loginFormWelcomeLabel"))
        loginFormUsernameLabel.textProperty().bind(StringPropertyManager.createBinding("loginFormUsernameLabel"))
        loginFormPasswordLabel.textProperty().bind(StringPropertyManager.createBinding("loginFormPasswordLabel"))
        loginFormLoginButton.textProperty().bind(StringPropertyManager.createBinding("loginFormLoginButton"))
        loginFormRegisterButton.textProperty().bind(StringPropertyManager.createBinding("loginFormRegisterButton"))
        loginFormLanguageLabel.textProperty().bind(StringPropertyManager.createBinding("loginFormLanguageLabel"))
        loginFormLanguageChoice.apply {
            converter = LocaleConverter()
            items = FXCollections.observableArrayList(StringPropertyManager.supportedLocales.keys)
            value = StringPropertyManager.locale
            onAction = EventHandler {
                StringPropertyManager.locale = value ?: Locale.getDefault()
            }
        }
    }
}