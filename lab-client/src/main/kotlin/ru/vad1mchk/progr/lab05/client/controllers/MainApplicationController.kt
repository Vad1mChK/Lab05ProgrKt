package ru.vad1mchk.progr.lab05.client.controllers

import javafx.collections.FXCollections
import javafx.event.EventTarget
import javafx.fxml.FXML
import javafx.scene.Parent
import javafx.scene.layout.*
import javafx.scene.control.*
import javafx.scene.control.cell.ChoiceBoxTableCell
import ru.vad1mchk.progr.lab05.client.strings.StringPropertyManager
import ru.vad1mchk.progr.lab05.client.util.FlatSpaceMarine
import ru.vad1mchk.progr.lab05.client.util.MeleeWeaponConverter
import ru.vad1mchk.progr.lab05.common.datatypes.MeleeWeapon
import tornadofx.*
import java.time.LocalDate

class MainApplicationController: Controller() {
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
        mainApplicationTableBox.createTable().also {
            it.items.addAll(
                FlatSpaceMarine(1, "Иван Усков","Феникс Райт", 837, 888.0f, LocalDate.now(), 3.14, 3, true, MeleeWeapon.POWER_SWORD, "Первое дело", "PW:AA", 4),
                FlatSpaceMarine(2, "Максим Коробков","Майлз Эджворт", 88, 888.0f, LocalDate.now(), 7.0, 3, false, MeleeWeapon.CHAIN_SWORD,"Дело о посетителе", null, 4)
            )
        }
    }

    fun EventTarget.createTable(): TableView<FlatSpaceMarine> = this.tableview(FXCollections.observableArrayList()) {
        readonlyColumn("ID", FlatSpaceMarine::id)
        readonlyColumn("Name", FlatSpaceMarine::name).also {
            it.textProperty().bind(StringPropertyManager.createBinding("propertyNameName"))
        }
        readonlyColumn("X", FlatSpaceMarine::coordinatesX).also {
            it.textProperty().bind(StringPropertyManager.createBinding("propertyNameCoordinateX"))
        }
        readonlyColumn("Y", FlatSpaceMarine::coordinatesY).also {
            it.textProperty().bind(StringPropertyManager.createBinding("propertyNameCoordinateY"))
        }
        readonlyColumn("Creation date", FlatSpaceMarine::creationDate).also {
            it.textProperty().bind(StringPropertyManager.createBinding("propertyNameCreationDate"))
        }
        readonlyColumn("Health", FlatSpaceMarine::health).also {
            it.textProperty().bind(StringPropertyManager.createBinding("propertyNameHealth"))
        }
        readonlyColumn("Heart count", FlatSpaceMarine::heartCount).also {
            it.textProperty().bind(StringPropertyManager.createBinding("propertyNameHeartCount"))
        }
        readonlyColumn("Loyal", FlatSpaceMarine::loyal).also {
            it.textProperty().bind(StringPropertyManager.createBinding("propertyNameLoyal"))
        }
        readonlyColumn("Melee weapon", FlatSpaceMarine::meleeWeapon).also {
            it.textProperty().bind(StringPropertyManager.createBinding("propertyNameMeleeWeapon"))
            it.setCellFactory {
                ChoiceBoxTableCell(MeleeWeaponConverter())
            }
        }
        readonlyColumn("Chapter name", FlatSpaceMarine::chapterName).also {
            it.textProperty().bind(StringPropertyManager.createBinding("propertyNameChapterName"))
        }
        readonlyColumn("Parent legion", FlatSpaceMarine::chapterParentLegion).also {
            it.textProperty().bind(StringPropertyManager.createBinding("propertyNameChapterParentLegion"))
        }
        readonlyColumn("Marines count", FlatSpaceMarine::chapterMarinesCount).also {
            it.textProperty().bind(StringPropertyManager.createBinding("propertyNameChapterMarinesCount"))
        }
        columnResizePolicy = SmartResize.POLICY
        isEditable = false
    }
}