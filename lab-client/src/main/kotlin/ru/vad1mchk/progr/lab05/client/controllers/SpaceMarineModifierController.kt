package ru.vad1mchk.progr.lab05.client.controllers

import javafx.beans.value.ChangeListener
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.layout.VBox
import ru.vad1mchk.progr.lab05.client.strings.StringPropertyManager
import ru.vad1mchk.progr.lab05.client.util.FlatSpaceMarine
import ru.vad1mchk.progr.lab05.client.util.MeleeWeaponConverter
import ru.vad1mchk.progr.lab05.common.datatypes.MeleeWeapon
import java.text.MessageFormat
import java.text.ParseException

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
    lateinit var spaceMarineHeartCountSlider: Slider
    @FXML
    lateinit var spaceMarineModifierLoyalLabel: Label
    @FXML
    lateinit var spaceMarineModifierLoyalBox: CheckBox
    @FXML
    lateinit var spaceMarineModifierMeleeWeaponLabel: Label
    @FXML
    lateinit var spaceMarineModifierMeleeWeaponChoice: ChoiceBox<MeleeWeapon?>
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
        var latestName: String = ""
        var latestCoordinateX: Int? = null
        var latestCoordinateY: Float? = null
        var latestHealth: Double? = null
        var latestHeartCount: Long? = 1
        var latestLoyal: Boolean = false
        var latestMeleeWeapon: MeleeWeapon? = null
        var latestChapterName: String = ""
        var latestChapterParentLegion: String = ""
        var latestChapterMarinesCount: Int? = null
    }

    fun initialize(flatSpaceMarine: FlatSpaceMarine?) {
        spaceMarineModifierBackground.styleClass.add("background")
        spaceMarineModifierNameLabel.textProperty().bind(
            StringPropertyManager.createBinding("propertyNameName")
        )
        spaceMarineModifierNameField.textProperty().addListener { observable, oldValue, newValue ->
            latestName = newValue
        }
        spaceMarineModifierCoordinatesLabel.textProperty().bind(
            StringPropertyManager.createBinding("propertyNameCoordinates")
        )
        spaceMarineModifierCoordinatesXField.textProperty().addListener { observable, oldValue, newValue ->
            try {
                latestCoordinateX = StringPropertyManager.integerFormat.parse(newValue).toInt()
            } catch (e: ParseException) {
                latestCoordinateX = null
            }
        }
        spaceMarineModifierCoordinatesYField.textProperty().addListener { observable, oldValue, newValue ->
            try {
                latestCoordinateY = StringPropertyManager.numberFormat.parse(newValue).toFloat()
            } catch (e: ParseException) {
                latestCoordinateY = null
            }
        }
        spaceMarineModifierHealthLabel.textProperty().bind(
            StringPropertyManager.createBinding("propertyNameHealth")
        )
        spaceMarineModifierHealthField.textProperty().addListener { observable, oldValue, newValue ->
            try {
                latestHealth = StringPropertyManager.numberFormat.parse(newValue).toDouble()
            } catch (e: ParseException) {
                latestHealth = null
            }
        }
        spaceMarineModifierHeartCountLabel.textProperty().bind(
            StringPropertyManager.createBinding("propertyNameHeartCount")
        )
        spaceMarineHeartCountSlider.valueProperty().addListener { observable, oldValue, newValue ->
            latestHeartCount = newValue.toLong()
        }
        spaceMarineModifierLoyalLabel.textProperty().bind(StringPropertyManager.createBinding("propertyNameLoyal"))
        spaceMarineModifierLoyalBox.selectedProperty().addListener { observable, oldValue, newValue ->
            latestLoyal = newValue
        }
        spaceMarineModifierMeleeWeaponLabel.textProperty().bind(
            StringPropertyManager.createBinding("propertyNameMeleeWeapon")
        )
        spaceMarineModifierMeleeWeaponChoice.apply {
            value = latestMeleeWeapon
            converter = object : MeleeWeaponConverter() {
                override fun toString(meleeWeapon: MeleeWeapon?): String {
                    return super.toString(meleeWeapon)?:StringPropertyManager["propertyValueNull"]
                }
            }
            items.addAll(null, *MeleeWeapon.values())
            valueProperty().addListener { observable, oldValue, newValue ->
                latestMeleeWeapon = newValue
            }
        }
        spaceMarineModifierChapterNameLabel.textProperty().bind(
            StringPropertyManager.createBinding("propertyNameChapterName")
        )
        spaceMarineModifierChapterNameField.textProperty().addListener { observable, oldValue, newValue ->
            latestChapterName = newValue
        }
        spaceMarineModifierChapterParentLegionField.textProperty().addListener { observable, oldValue, newValue ->
            latestChapterParentLegion = newValue
        }
        spaceMarineModifierChapterMarinesCountField.textProperty().addListener { observable, oldValue, newValue ->
            try {
                latestChapterMarinesCount = StringPropertyManager.integerFormat.parse(newValue).toInt()
            } catch (e: ParseException) {
                latestChapterMarinesCount = null
            }
        }
        spaceMarineModifierChapterParentLegionLabel.textProperty().bind(
            StringPropertyManager.createBinding("propertyNameChapterParentLegion")
        )
        spaceMarineModifierChapterMarinesCountLabel.textProperty().bind(
            StringPropertyManager.createBinding("propertyNameChapterMarinesCount")
        )
        spaceMarineModifierSubmitButton.textProperty().bind(
            StringPropertyManager.createBinding("spaceMarineModifierSubmit")
        )
        if (flatSpaceMarine != null) {
            spaceMarineModifierLabel.textProperty().bind(StringPropertyManager.createBinding {
                val messageFormat = MessageFormat(StringPropertyManager["spaceMarineModifierLabelUpdate"])
                messageFormat.format(flatSpaceMarine.id)
            })
            spaceMarineModifierNameField.text = flatSpaceMarine.name
        } else {
            spaceMarineModifierLabel.textProperty().bind(
                StringPropertyManager.createBinding("spaceMarineModifierLabelCreate")
            )
            spaceMarineModifierNameField.text = latestName
            spaceMarineModifierCoordinatesXField.text = latestCoordinateX?.let{
                StringPropertyManager.integerFormat.format(it)
            }?:""
            spaceMarineModifierCoordinatesYField.text = latestCoordinateY?.let {
                StringPropertyManager.numberFormat.format(it)
            }?:""
            spaceMarineModifierHealthField.text = latestHealth?.let {
                StringPropertyManager.numberFormat.format(it)
            }?:""
            spaceMarineHeartCountSlider.value = (latestHeartCount ?: 1L).toDouble()
            spaceMarineModifierLoyalBox.isSelected = latestLoyal ?: false
            spaceMarineModifierMeleeWeaponChoice.value = latestMeleeWeapon
            spaceMarineModifierChapterNameField.text = latestChapterName
            spaceMarineModifierChapterParentLegionField.text = latestChapterParentLegion
            spaceMarineModifierChapterMarinesCountField.text = latestChapterMarinesCount?.let {
                StringPropertyManager.numberFormat.format(it)
            }?:""
        }
    }
}