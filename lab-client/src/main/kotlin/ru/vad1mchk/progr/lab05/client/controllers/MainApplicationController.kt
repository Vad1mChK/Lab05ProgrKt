package ru.vad1mchk.progr.lab05.client.controllers

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.value.ChangeListener
import javafx.collections.FXCollections
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.control.cell.ChoiceBoxTableCell
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.input.MouseEvent
import javafx.scene.layout.*
import javafx.scene.text.Text
import javafx.util.Callback
import ru.vad1mchk.progr.lab05.client.application.CollectionInformation
import ru.vad1mchk.progr.lab05.client.application.SpaceMarineInformation
import ru.vad1mchk.progr.lab05.client.application.UserInformation
import ru.vad1mchk.progr.lab05.client.strings.StringPropertyManager
import ru.vad1mchk.progr.lab05.client.strings.Strings
import ru.vad1mchk.progr.lab05.client.util.*
import ru.vad1mchk.progr.lab05.client.util.NewStageOpener.Companion.decorateStage
import ru.vad1mchk.progr.lab05.common.datatypes.MeleeWeapon
import tornadofx.*
import java.sql.Date
import java.text.MessageFormat
import java.time.LocalDate
import java.util.*

class MainApplicationController: Controller() {
    @FXML
    lateinit var mainApplicationAboutText: Text
    @FXML
    lateinit var mainApplicationSettingsDemonstrationLabel: Label
    @FXML
    lateinit var mainApplicationSettingsDemonstrationScrollPane: ScrollPane
    @FXML
    lateinit var mainApplicationSettingsDemonstrationText: Text
    @FXML
    lateinit var mainApplicationSettingsUserInfoButton: Button
    @FXML
    lateinit var mainApplicationSettingsUserInfoLabel: Label
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
    lateinit var mainApplicationMapScrollPane: ScrollPane
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

    lateinit var collectionInformation: CollectionInformation
    lateinit var spaceMarineInformation: SpaceMarineInformation
    fun initialize() {
        mainApplicationBackground.styleClass.add("background")
        collectionInformation = CollectionInformation(
            mainApplicationTableTable.itemsProperty(), Configuration.user!!.userName
        )
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
        mainApplicationMapInfoButton.apply {
            textProperty().bind(StringPropertyManager.createBinding("mainApplicationInfoCommand"))
        }
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
        mainApplicationSettingsUserInfoLabel.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationSettingsUser")
        )
        mainApplicationSettingsUserInfoButton.apply {
            textProperty().bind(StringPropertyManager.createBinding("mainApplicationSettingsUserInfo"))
            onMouseClicked = EventHandler {
                UserInformation(
                    Configuration.user!!.userName,
                    mainApplicationTableTable.items.count { it.creatorName == Configuration.user!!.userName }
                ).draw()
            }
        }
        mainApplicationSettingsDemonstrationLabel.textProperty().bind(
            StringPropertyManager.createBinding("mainApplicationSettingsDemonstration")
        )

        mainApplicationSettingsDemonstrationText.apply {
            textProperty().bind(StringPropertyManager.createBinding {
                val messageFormat = MessageFormat(StringPropertyManager["mainApplicationSettingsFormats"])
                val number=Configuration.spaceMarinesCreated
                messageFormat.locale = StringPropertyManager.locale
                messageFormat.format( arrayOf(
                    StringPropertyManager.dateFormat.format(
                        Date.valueOf(LocalDate.of(1922, 12, 30))
                    ),
                    StringPropertyManager.numberFormat.format(3.14159265),
                    StringPropertyManager.dateFormat.format(Date.valueOf(LocalDate.now())),
                    Configuration.user?.userName,
                    StringPropertyManager.integerFormat.format(number.toInt()),
                    CustomChoiceFormat.formatByRules {
                        when(number% 100u) {
                            1u, in (21u..91u step 10) -> {
                                StringPropertyManager["mainApplicationSettingsFormatSpaceMarineSingular"]
                            }
                            else -> {
                                StringPropertyManager["mainApplicationSettingsFormatSpaceMarinePlural"]
                            }
                        }
                    }
                ))
            })
        }

        mainApplicationAboutText.apply {
            textProperty().bind(StringPropertyManager.createBinding {
                val messageFormat = MessageFormat(StringPropertyManager["mainApplicationAboutText"])
                messageFormat.locale = StringPropertyManager.locale
                messageFormat.format(arrayOf(
                    Strings.VERSION,
                    Strings.LABORATORY_WORK_NUMBER,
                    StringPropertyManager["disciplineName"],
                    Strings.PROGRAMMING_LANGUAGE,
                    Strings.PROGRAMMING_LANGUAGE_VERSION,
                    Strings.JAVAFX_VERSION,
                    Strings.JDK_VERSION,
                    StringPropertyManager["authorOne"],
                    StringPropertyManager["authorTwo"]
                ))
            })
        }

        StringPropertyManager.localeProperty.addListener(ChangeListener { observable, oldValue, newValue ->
            refreshTable(mainApplicationTableTable)
        })
    }

    private fun addColumnsToTable(table: TableView<FlatSpaceMarine>): TableView<FlatSpaceMarine> {
        val idColumn = TableColumn<FlatSpaceMarine, Int?>().apply {
            text = "ID"
            cellValueFactory = PropertyValueFactory("id")
            setCellFactory {
                val cell = object : TableCell<FlatSpaceMarine, Int?>() {
                    override fun updateItem(item: Int?, empty: Boolean) {
                        super.updateItem(item, empty)
                        this.text = if(empty) null else string
                    }

                    val string: String
                        get() = item?.let { StringPropertyManager.integerFormat.format(it) } ?: ""
                }
                cell.addEventFilter(MouseEvent.MOUSE_CLICKED) {
                    displaySpaceMarineInformation(table.items.first { it.id == cell.item })
                }
                cell
            }
        }
        val creatorNameColumn = TableColumn<FlatSpaceMarine, String?>().apply {
            textProperty().bind(StringPropertyManager.createBinding("propertyNameCreator"))
            cellValueFactory = PropertyValueFactory("creatorName")
            cellFactory = Callback<TableColumn<FlatSpaceMarine, String?>, TableCell<FlatSpaceMarine, String?>> {
                val cell: TableCell<FlatSpaceMarine, String?> = object: TableCell<FlatSpaceMarine, String?>() {
                    override fun updateItem(item: String?, empty: Boolean) {
                        super.updateItem(item, empty)
                        this.text = if(empty) null else string
                        if (item == null) {
                            styleClass.add("translucent-text")
                        } else {
                            styleClass.remove("translucent-text")
                        }
                    }

                    val string: String
                        get() = item ?: StringPropertyManager["userInformationServer"]
                }
                cell.addEventFilter(MouseEvent.MOUSE_CLICKED) { event -> UserInformation(
                    cell.item, table.items.count { it.creatorName == cell.item }
                ).draw() }
                cell
            }
        }
        val nameColumn = TableColumn<FlatSpaceMarine, String>().apply {
            textProperty().bind(StringPropertyManager.createBinding("propertyNameName"))
            setCellValueFactory(PropertyValueFactory("name"))
        }
        val coordinateXColumn = TableColumn<FlatSpaceMarine, Int?>().apply {
            textProperty().bind(StringPropertyManager.createBinding("propertyNameCoordinateX"))
            cellValueFactory = PropertyValueFactory("coordinatesX")
            setCellFactory {
                object : TableCell<FlatSpaceMarine, Int?>() {
                    override fun updateItem(item: Int?, empty: Boolean) {
                        super.updateItem(item, empty)
                        this.text = if(empty) null else string
                    }

                    val string: String
                        get() = item?.let { StringPropertyManager.integerFormat.format(it) } ?: ""
                }
            }
        }
        val coordinateYColumn = TableColumn<FlatSpaceMarine, Float?>().apply {
            textProperty().bind(StringPropertyManager.createBinding("propertyNameCoordinateY"))
            cellValueFactory = PropertyValueFactory("coordinatesY")
            setCellFactory {
                object : TableCell<FlatSpaceMarine, Float?>() {
                    override fun updateItem(item: Float?, empty: Boolean) {
                        super.updateItem(item, empty)
                        this.text = if(empty) null else string
                    }

                    val string: String
                        get() = item?.let { StringPropertyManager.numberFormat.format(it) } ?: ""
                }
            }
        }
        val creationDateColumn = TableColumn<FlatSpaceMarine, LocalDate?>().apply {
            textProperty().bind(StringPropertyManager.createBinding("propertyNameCreationDate"))
            cellValueFactory = PropertyValueFactory("creationDate")
            setCellFactory {
                object : TableCell<FlatSpaceMarine, LocalDate?>() {
                    override fun updateItem(item: LocalDate?, empty: Boolean) {
                        super.updateItem(item, empty)
                        this.text = if(empty) null else string
                    }

                    val string: String
                        get() = item?.let { StringPropertyManager.dateFormat.format(Date.valueOf(it)) } ?: ""
                }
            }
        }
        val healthColumn = TableColumn<FlatSpaceMarine, Double?>().apply {
            textProperty().bind(StringPropertyManager.createBinding("propertyNameHealth"))
            cellValueFactory = PropertyValueFactory("health")
            setCellFactory {
                object : TableCell<FlatSpaceMarine, Double?>() {
                    override fun updateItem(item: Double?, empty: Boolean) {
                        super.updateItem(item, empty)
                        this.text = if(empty) null else string
                    }

                    val string: String
                        get() = item?.let { StringPropertyManager.numberFormat.format(it) } ?: ""
                }
            }
        }
        val heartCountColumn = TableColumn<FlatSpaceMarine, Long>().apply {
            textProperty().bind(StringPropertyManager.createBinding("propertyNameHeartCount"))
            cellValueFactory = PropertyValueFactory("heartCount")
            setCellFactory {
                object : TableCell<FlatSpaceMarine, Long?>() {
                    override fun updateItem(item: Long?, empty: Boolean) {
                        super.updateItem(item, empty)
                        this.text = if(empty) null else string
                    }

                    val string: String
                        get() = item?.let { StringPropertyManager.integerFormat.format(it) } ?: ""
                }
            }
        }
        val loyalColumn = TableColumn<FlatSpaceMarine, Boolean>().apply {
            textProperty().bind(StringPropertyManager.createBinding("propertyNameLoyal"))
            cellValueFactory = PropertyValueFactory("loyal")
            setCellFactory { ChoiceBoxTableCell(BooleanConverter()) }
        }
        val meleeWeaponColumn = TableColumn<FlatSpaceMarine, MeleeWeapon?>().apply {
            textProperty().bind(StringPropertyManager.createBinding("propertyNameMeleeWeapon"))
            cellValueFactory = PropertyValueFactory("meleeWeapon")
            setCellFactory { ChoiceBoxTableCell(MeleeWeaponConverter()) }
        }
        val chapterNameColumn = TableColumn<FlatSpaceMarine, String?>().apply {
            textProperty().bind(StringPropertyManager.createBinding("propertyNameChapterName"))
            setCellValueFactory(PropertyValueFactory("chapterName"))
        }
        val chapterParentLegionColumn = TableColumn<FlatSpaceMarine, String?>().apply {
            textProperty().bind(StringPropertyManager.createBinding("propertyNameChapterParentLegion"))
            setCellValueFactory(PropertyValueFactory("chapterParentLegion"))
        }
        val chapterMarinesCountColumn = TableColumn<FlatSpaceMarine, Int?>().apply {
            textProperty().bind(StringPropertyManager.createBinding("propertyNameChapterMarinesCount"))
            cellValueFactory = PropertyValueFactory("chapterMarinesCount")
            setCellFactory {
                object : TableCell<FlatSpaceMarine, Int?>() {
                    override fun updateItem(item: Int?, empty: Boolean) {
                        super.updateItem(item, empty)
                        this.text = if(empty) null else string
                    }
                    val string: String
                        get() = item?.let { StringPropertyManager.integerFormat.format(it) } ?: ""
                }
            }
        }
        table.columns.setAll(
            idColumn, creatorNameColumn, nameColumn, coordinateXColumn, coordinateYColumn, creationDateColumn,
            healthColumn, heartCountColumn, loyalColumn, meleeWeaponColumn, chapterNameColumn,
            chapterParentLegionColumn, chapterMarinesCountColumn
        )

        table.columnResizePolicy = SmartResize.POLICY
        return table
    }

    private fun refreshTable(table: TableView<FlatSpaceMarine>) {
        val temporaryStorage = LinkedList(table.items)
        table.items.clear()
        table.items.setAll(temporaryStorage)
    }

    fun updateTable(list: LinkedList<FlatSpaceMarine>) {
        mainApplicationTableTable.items.clear()
        mainApplicationTableTable.items.setAll(list)
    }

    fun displaySpaceMarineInformation(flatSpaceMarine: FlatSpaceMarine) {
        spaceMarineInformation = SpaceMarineInformation(flatSpaceMarine)
        spaceMarineInformation.draw()
    }
}