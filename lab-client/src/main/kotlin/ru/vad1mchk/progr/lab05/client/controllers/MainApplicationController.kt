package ru.vad1mchk.progr.lab05.client.controllers

import javafx.fxml.FXML
import javafx.scene.layout.*
import javafx.scene.control.*
import ru.vad1mchk.progr.lab05.client.strings.StringPropertyManager
import tornadofx.Controller

class MainApplicationController: Controller() {
    @FXML
    lateinit var mainApplicationAboutLabel: Label
    @FXML
    lateinit var mainApplicationSettingsLabel: Label
    @FXML
    lateinit var mainApplicationTableLabel: Label
    @FXML
    lateinit var mainApplicationMapLabel: Label
    @FXML
    lateinit var mainApplicationAboutTab: Tab
    @FXML
    lateinit var mainApplicationSettingsTab: Tab
    @FXML
    lateinit var mainApplicationTableTab: Tab
    @FXML
    lateinit var mainApplicationMapTab: Tab
    @FXML
    lateinit var mainApplicationTabPane: TabPane
    @FXML
    lateinit var mainApplicationBackground: AnchorPane

    fun initialize() {
        mainApplicationMapTab.textProperty().bind(StringPropertyManager.createBinding(""))
    }
}