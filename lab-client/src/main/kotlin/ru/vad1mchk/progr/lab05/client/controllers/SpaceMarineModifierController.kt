package ru.vad1mchk.progr.lab05.client.controllers

import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.layout.VBox
import ru.vad1mchk.progr.lab05.client.strings.StringPropertyManager
import ru.vad1mchk.progr.lab05.client.util.FlatSpaceMarine
import ru.vad1mchk.progr.lab05.common.datatypes.MeleeWeapon
import java.text.MessageFormat

class SpaceMarineModifierController {
    @FXML
    lateinit var spaceMarineModifierBackground: VBox
    @FXML
    lateinit var spaceMarineModifierLabel: Label
    @FXML
    lateinit var spaceMarineModifierNameLabel: Label
    @FXML
    lateinit var spaceMarineModifierNameField: TextField
    @FXML
    lateinit var spaceMarineModifierCoordinatesLabel: Label
    @FXML
    lateinit var spaceMarineModifierCoordinatesXField: TextField
    @FXML
    lateinit var spaceMarineModifierCoordinatesYField: TextField
    @FXML
    lateinit var spaceMarineModifierHealthLabel: Label
    @FXML
    lateinit var spaceMarineModifierHealthField: TextField
    @FXML
    lateinit var spaceMarineModifierHeartCountLabel: Label
    @FXML
    lateinit var spaceMarineHeartCount: Slider
    @FXML
    lateinit var spaceMarineModifierLoyalLabel: Label
    @FXML
    lateinit var spaceMarineModifierLoyalLabelBox: CheckBox
    @FXML
    lateinit var spaceMarineModifierMeleeWeaponLabel: Label
    @FXML
    lateinit var spaceMarineModifierMeleeWeaponChoice: ChoiceBox<Any>
    @FXML
    lateinit var spaceMarineModifierChapterNameLabel: Label
    @FXML
    lateinit var spaceMarineModifierChapterNameField: TextField
    @FXML
    lateinit var spaceMarineModifierChapterParentLegionLabel: Label
    @FXML
    lateinit var spaceMarineModifierChapterParentLegionField: TextField
    @FXML
    lateinit var spaceMarineModifierChapterMarinesCountLabel: Label
    @FXML
    lateinit var spaceMarineModifierChapterMarinesCountField: TextField
    @FXML
    lateinit var spaceMarineModifierSubmitButton: Button

    private companion object {
        var latestName: String? = null
        var latestCoordinateX: Int? = null
        var latestCoordinateY: Float? = null
        var latestHealth: Double? = null
        var latestHeartCount: Long? = 1
        var latestLoyal: Boolean = false
        var latestMeleeWeapon: MeleeWeapon? = null
        var latestChapterName: String? = null
        var latestChapterParentLegion: String? = null
        var latestChapterMarinesCount: Int? = null
    }

    fun initialize(flatSpaceMarine: FlatSpaceMarine?) {
        spaceMarineModifierBackground.styleClass.add("background")
        spaceMarineModifierNameLabel.textProperty().bind(
            StringPropertyManager.createBinding("propertyNameName")
        )
        spaceMarineModifierNameField.onInputMethodTextChanged = EventHandler {
            println(spaceMarineModifierNameField.text)
        }
        spaceMarineModifierCoordinatesLabel.textProperty().bind(
            StringPropertyManager.createBinding("propertyNameCoordinates")
        )
        spaceMarineModifierHealthLabel.textProperty().bind(
            StringPropertyManager.createBinding("propertyNameHealth")
        )
        spaceMarineModifierHeartCountLabel.textProperty().bind(
            StringPropertyManager.createBinding("propertyNameHeartCount")
        )
        spaceMarineModifierLoyalLabel.textProperty().bind(StringPropertyManager.createBinding("propertyNameLoyal"))
        spaceMarineModifierMeleeWeaponLabel.textProperty().bind(
            StringPropertyManager.createBinding("propertyNameMeleeWeapon")
        )
        spaceMarineModifierChapterNameLabel.textProperty().bind(
            StringPropertyManager.createBinding("propertyNameChapterName")
        )
        spaceMarineModifierChapterParentLegionLabel.textProperty().bind(
            StringPropertyManager.createBinding("propertyNameChapterParentLegion")
        )
        spaceMarineModifierChapterMarinesCountLabel.textProperty().bind(
            StringPropertyManager.createBinding("propertyNameChapterMarinesCount")
        )
        if (flatSpaceMarine != null) {
            spaceMarineModifierLabel.textProperty().bind(StringPropertyManager.createBinding {
                val messageFormat = MessageFormat(StringPropertyManager["spaceMarineModifierLabelUpdate"])
                messageFormat.format(flatSpaceMarine.id)
            })
            spaceMarineModifierNameField.text = flatSpaceMarine.name
        }
    }

    fun setLatest(flatSpaceMarine: FlatSpaceMarine) {
        latestName = flatSpaceMarine.name
        latestCoordinateX = flatSpaceMarine.coordinatesX
        latestCoordinateY = flatSpaceMarine.coordinatesY
        latestHealth = flatSpaceMarine.health
        latestHeartCount = flatSpaceMarine.heartCount
        latestLoyal = flatSpaceMarine.loyal
        latestMeleeWeapon = flatSpaceMarine.meleeWeapon
        latestChapterName = flatSpaceMarine.chapterName
        latestChapterParentLegion = flatSpaceMarine.chapterParentLegion
        latestChapterMarinesCount = flatSpaceMarine.chapterMarinesCount
    }
}