package ru.vad1mchk.progr.lab05.client.controllers

import javafx.fxml.FXML
import javafx.scene.canvas.Canvas
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.layout.VBox
import javafx.scene.shape.SVGPath
import javafx.scene.text.Text
import javafx.scene.web.WebView
import ru.vad1mchk.progr.lab05.client.strings.StringPropertyManager
import ru.vad1mchk.progr.lab05.client.styles.Colors
import ru.vad1mchk.progr.lab05.client.util.BooleanConverter
import ru.vad1mchk.progr.lab05.client.util.Configuration
import ru.vad1mchk.progr.lab05.client.util.MeleeWeaponConverter
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import tornadofx.Controller
import java.awt.image.BufferedImage
import java.security.MessageDigest
import java.sql.Date
import java.text.MessageFormat

class SpaceMarineInformationController: Controller() {
    @FXML
    lateinit var spaceMarineInformationBackground: VBox

    @FXML
    lateinit var spaceMarineInformationLabel: Label

    @FXML
    lateinit var spaceMarineInformationName: Label

    @FXML
    lateinit var spaceMarineInformationId: Label

    @FXML
    lateinit var spaceMarineInformationPicture: Canvas

    @FXML
    lateinit var spaceMarineInformationData: Text

    companion object {
        private val booleanConverter = BooleanConverter()
        private val meleeWeaponConverter = MeleeWeaponConverter()
    }

    fun initialize(spaceMarine: SpaceMarine) {
        spaceMarineInformationBackground.styleClass.add("background")
        spaceMarineInformationLabel.textProperty().bind(
            StringPropertyManager.createBinding("collectionInformationLabel")
        )
        spaceMarineInformationName.text = spaceMarine.name
        spaceMarineInformationId.text = "#${spaceMarine.id}"
        spaceMarineInformationPicture.graphicsContext2D.apply {
            appendSVGPath(Configuration.spaceMarineDrawingPath)
            if (spaceMarine.creatorName != null) {
                fill = Colors.colorRgbFromHashCode(spaceMarine.creatorName)
                fill()
            } else {
                stroke = Colors.colorRgbFromHashCode(spaceMarine.creatorName)
                lineWidth = 8.0
                stroke()
            }
        }
        spaceMarineInformationData.textProperty().bind(StringPropertyManager.createBinding {
            if (spaceMarine.chapter == null) {
                val messageFormat = MessageFormat(StringPropertyManager["spaceMarineInformationWithNullChapter"])
                messageFormat.locale = StringPropertyManager.locale
                messageFormat.format(
                    arrayOf(
                        spaceMarine.creatorName ?: StringPropertyManager["userInformationServer"],
                        spaceMarine.coordinates.x,
                        spaceMarine.coordinates.y,
                        StringPropertyManager.dateFormat.format(Date.valueOf(spaceMarine.creationDate)),
                        spaceMarine.health,
                        spaceMarine.heartCount,
                        booleanConverter.toString(spaceMarine.loyal),
                        meleeWeaponConverter.toString(spaceMarine.meleeWeapon)
                            ?: StringPropertyManager["propertyValueNull"]
                    )
                )
            } else {
                val messageFormat = MessageFormat(StringPropertyManager["spaceMarineInformationWithNonNullChapter"])
                messageFormat.locale = StringPropertyManager.locale
                messageFormat.format(
                    arrayOf(
                        spaceMarine.creatorName ?: StringPropertyManager["userInformationServer"],
                        spaceMarine.coordinates.x,
                        spaceMarine.coordinates.y,
                        StringPropertyManager.dateFormat.format(Date.valueOf(spaceMarine.creationDate)),
                        spaceMarine.health,
                        spaceMarine.heartCount,
                        booleanConverter.toString(spaceMarine.loyal),
                        meleeWeaponConverter.toString(spaceMarine.meleeWeapon)
                            ?: StringPropertyManager["propertyValueNull"],
                        spaceMarine.chapter!!.name,
                        spaceMarine.chapter!!.parentLegion ?: StringPropertyManager["propertyValueNull"],
                        spaceMarine.chapter!!.marinesCount
                    )
                )
            }
        })
    }
}