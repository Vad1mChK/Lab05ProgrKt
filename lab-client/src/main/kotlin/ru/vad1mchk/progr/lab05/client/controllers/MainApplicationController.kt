package ru.vad1mchk.progr.lab05.client.controllers

import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.event.EventHandler
import javafx.event.EventTarget
import javafx.fxml.FXML
import javafx.scene.Parent
import javafx.scene.layout.*
import javafx.scene.control.*
import javafx.scene.control.cell.ChoiceBoxTableCell
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.text.Text
import javafx.util.Callback
import javafx.util.converter.IntegerStringConverter
import javafx.util.converter.NumberStringConverter
import ru.vad1mchk.progr.lab05.client.strings.StringPropertyManager
import ru.vad1mchk.progr.lab05.client.util.*
import ru.vad1mchk.progr.lab05.common.datatypes.MeleeWeapon
import tornadofx.*
import java.time.LocalDate
import java.sql.Date
import java.util.*

class MainApplicationController: Controller() {
    @FXML
    lateinit var mainApplicationSettingsDemonstrationLabel: Label
    @FXML
    lateinit var mainApplicationSettingsDemonstrationText: Text
    @FXML
    lateinit var mainApplicationSettingsLanguageLabel: Label
    @FXML
    lateinit var mainApplicationSettingsLanguageChoice: ChoiceBox<Locale>
    @FXML
    lateinit var mainApplicationTableTable: TableView<FlatSpaceMarine>
    @FXML
    lateinit var mainApplicationTableBox: VBox
    @FXML
    lateinit var mainApplicationTableCreateButton: Button
    @FXML
    lateinit var mainApplicationTableUpdateButton: Button
    @FXML
    lateinit var mainApplicationTableDeleteButton: Button
    @FXML
    lateinit var mainApplicationTableClearButton: Button
    @FXML
    lateinit var mainApplicationTableFilterButton: Button
    @FXML
    lateinit var mainApplicationTableInfoButton: Button
    @FXML
    lateinit var mainApplicationTableAddIfMinButton: Button
    @FXML
    lateinit var mainApplicationTableDeleteGreaterButton: Button
    @FXML
    lateinit var mainApplicationTableHistoryButton: Button
    @FXML
    lateinit var mainApplicationTablePrintFieldDescendingHealthButton: Button
    @FXML
    lateinit var mainApplicationTableExecuteScriptButton: Button
    @FXML
    lateinit var mainApplicationMapCreateButton: Button
    @FXML
    lateinit var mainApplicationMapUpdateButton: Button
    @FXML
    lateinit var mainApplicationMapDeleteButton: Button
    @FXML
    lateinit var mainApplicationMapClearButton: Button
    @FXML
    lateinit var mainApplicationMapFilterButton: Button
    @FXML
    lateinit var mainApplicationMapInfoButton: Button
    @FXML
    lateinit var mainApplicationMapAddIfMinButton: Button
    @FXML
    lateinit var mainApplicationMapDeleteGreaterButton: Button
    @FXML
    lateinit var mainApplicationMapHistoryButton: Button
    @FXML
    lateinit var mainApplicationMapPrintFieldDescendingHealthButton: Button
    @FXML
    lateinit var mainApplicationMapExecuteScriptButton: Button
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
        mainApplicationMapTab.textProperty().bind(StringPropertyManager.createBinding("mainApplicationMapLabel"))
        mainApplicationMapLabel.textProperty().bind(StringPropertyManager.createBinding("mainApplicationMapLabel"))
        mainApplicationTableTab.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationTableLabel")
        )
        mainApplicationTableLabel.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationTableLabel")
        )
        mainApplicationSettingsTab.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationSettingsLabel")
        )
        mainApplicationSettingsLabel.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationSettingsLabel")
        )
        mainApplicationAboutTab.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationAboutLabel")
        )
        mainApplicationAboutLabel.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationAboutLabel")
        )
        mainApplicationMapCreateButton.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationCreateCommand")
        )
        mainApplicationTableCreateButton.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationCreateCommand")
        )
        mainApplicationMapUpdateButton.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationUpdateCommand")
        )
        mainApplicationTableUpdateButton.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationUpdateCommand")
        )
        mainApplicationMapDeleteButton.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationDeleteCommand")
        )
        mainApplicationTableDeleteButton.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationDeleteCommand")
        )
        mainApplicationMapClearButton.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationClearCommand")
        )
        mainApplicationTableClearButton.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationClearCommand")
        )
        mainApplicationMapFilterButton.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationFilterCommand")
        )
        mainApplicationTableFilterButton.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationFilterCommand")
        )
        mainApplicationMapInfoButton.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationInfoCommand")
        )
        mainApplicationTableInfoButton.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationInfoCommand")
        )
        mainApplicationMapAddIfMinButton.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationAddIfMinCommand")
        )
        mainApplicationTableAddIfMinButton.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationAddIfMinCommand")
        )
        mainApplicationMapDeleteGreaterButton.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationRemoveGreaterCommand")
        )
        mainApplicationTableDeleteGreaterButton.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationRemoveGreaterCommand")
        )
        mainApplicationMapHistoryButton.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationHistoryCommand")
        )
        mainApplicationTableHistoryButton.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationHistoryCommand")
        )
        mainApplicationMapPrintFieldDescendingHealthButton.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationPrintFieldDescendingHealthCommand")
        )
        mainApplicationTablePrintFieldDescendingHealthButton.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationPrintFieldDescendingHealthCommand")
        )
        mainApplicationMapExecuteScriptButton.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationExecuteScriptCommand")
        )
        mainApplicationTableExecuteScriptButton.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationExecuteScriptCommand")
        )
        addColumnsToTable(mainApplicationTableTable)
        mainApplicationTableTable.items.add(
            FlatSpaceMarine(3, "Иван Усков", "Майлз", 16777216, 3141.5926f, LocalDate.now(), 7.0, 3, false, MeleeWeapon.CHAIN_SWORD, null, null, null)
        )
        mainApplicationSettingsLanguageLabel.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationSettingsLanguage")
        )
        mainApplicationSettingsLanguageChoice.apply {
            converter = LocaleConverter()
            items = FXCollections.observableArrayList(StringPropertyManager.supportedLocales.keys)
            value = StringPropertyManager.locale
            onAction = EventHandler {
                StringPropertyManager.locale = value ?: Locale.getDefault()
            }
        }
        mainApplicationSettingsDemonstrationLabel.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationSettingsDemonstration")
        )

        mainApplicationSettingsDemonstrationText.apply {
            // Write code that formats the message and updates the format on locale change
        }
    }

    fun addColumnsToTable(table: TableView<FlatSpaceMarine>): TableView<FlatSpaceMarine> {
        val idColumn = TableColumn<FlatSpaceMarine, Int>().apply {
            text = "ID"
        }
        val creatorNameColumn = TableColumn<FlatSpaceMarine, String?>().apply {
            textProperty().bind(StringPropertyManager.createBinding("propertyNameCreator"))
            setCellValueFactory(PropertyValueFactory("creatorName"))
        }
        val nameColumn = TableColumn<FlatSpaceMarine, String>().apply {
            textProperty().bind(StringPropertyManager.createBinding("propertyNameName"))
            setCellValueFactory(PropertyValueFactory("name"))
        }
        val coordinateXColumn = TableColumn<FlatSpaceMarine, String>().apply {
            textProperty().bind(StringPropertyManager.createBinding("propertyNameCoordinateX"))
            setCellValueFactory { cell ->
                SimpleStringProperty(StringPropertyManager.integerFormat.format(cell.value.coordinatesX))
            }
        }
        val coordinateYColumn = TableColumn<FlatSpaceMarine, String>().apply {
            textProperty().bind(StringPropertyManager.createBinding("propertyNameCoordinateY"))
            setCellValueFactory { cell ->
                SimpleStringProperty(StringPropertyManager.numberFormat.format(cell.value.coordinatesY))
            }
        }
        val creationDateColumn = TableColumn<FlatSpaceMarine, String>().apply {
            textProperty().bind(StringPropertyManager.createBinding("propertyNameCreationDate"))
            setCellValueFactory { cell ->
                SimpleStringProperty(StringPropertyManager.dateFormat.format(Date.valueOf(cell.value.creationDate)))
            }
        }
        val healthColumn = TableColumn<FlatSpaceMarine, String>().apply {
            textProperty().bind(StringPropertyManager.createBinding("propertyNameHealth"))
            setCellValueFactory { cell ->
                SimpleStringProperty(StringPropertyManager.numberFormat.format(cell.value.health))
            }
        }
        val heartCountColumn = TableColumn<FlatSpaceMarine, String>().apply {
            textProperty().bind(StringPropertyManager.createBinding("propertyNameHeartCount"))
            setCellValueFactory { cell ->
                SimpleStringProperty(StringPropertyManager.integerFormat.format(cell.value.heartCount))
            }
        }
        val loyalColumn = TableColumn<FlatSpaceMarine, String>().apply {
            textProperty().bind(StringPropertyManager.createBinding("propertyNameLoyal"))
        }
        val meleeWeaponColumn = TableColumn<FlatSpaceMarine, String?>().apply {
            textProperty().bind(StringPropertyManager.createBinding("propertyNameMeleeWeapon"))
        }
        val chapterNameColumn = TableColumn<FlatSpaceMarine, String?>().apply {
            textProperty().bind(StringPropertyManager.createBinding("propertyNameChapterName"))
            setCellValueFactory(PropertyValueFactory("chapterName"))
        }
        val chapterParentLegionColumn = TableColumn<FlatSpaceMarine, String?>().apply {
            textProperty().bind(StringPropertyManager.createBinding("propertyNameChapterParentLegion"))
            setCellValueFactory(PropertyValueFactory("chapterParentLegion"))
        }
        val chapterMarinesCountColumn = TableColumn<FlatSpaceMarine, String?>().apply {
            textProperty().bind(StringPropertyManager.createBinding("propertyNameChapterMarinesCount"))
        }
        table.columns.addAll(
            idColumn, creatorNameColumn, nameColumn, coordinateXColumn, coordinateYColumn, creationDateColumn,
            healthColumn, heartCountColumn, loyalColumn, meleeWeaponColumn, chapterNameColumn,
            chapterParentLegionColumn, chapterMarinesCountColumn
        )
        table.columnResizePolicy = TableView.CONSTRAINED_RESIZE_POLICY
        return table
    }
}