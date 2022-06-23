package ru.vad1mchk.progr.lab05.client.controllers

import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.AnchorPane
import javafx.scene.text.Text
import ru.vad1mchk.progr.lab05.client.strings.StringPropertyManager
import tornadofx.Controller

class UserInformationController: Controller() {
    @FXML
    lateinit var userInformationBackground: AnchorPane

    @FXML
    lateinit var userInformationText: Text

    @FXML
    lateinit var userInformationLabel: Label

    fun initialize() {
        userInformationBackground.styleClass.add("background")
        userInformationLabel.textProperty().bind(StringPropertyManager.createBinding("userInformationLabel"))
    }
}