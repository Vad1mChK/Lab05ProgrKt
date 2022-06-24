package ru.vad1mchk.progr.lab05.client.controllers

import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.text.Text
import ru.vad1mchk.progr.lab05.client.strings.StringPropertyManager
import ru.vad1mchk.progr.lab05.client.styles.Colors
import ru.vad1mchk.progr.lab05.client.util.Configuration
import ru.vad1mchk.progr.lab05.client.util.CustomChoiceFormat
import tornadofx.Controller
import java.text.MessageFormat
import java.util.*

class UserInformationController: Controller() {
    @FXML
    lateinit var userInformationColorSquare: Pane

    @FXML
    lateinit var userInformationBackground: AnchorPane

    @FXML
    lateinit var userInformationText: Text

    @FXML
    lateinit var userInformationLabel: Label

    var userName: String? = null
    lateinit var userColor: Color

    fun initialize(userName: String?, userMarinesCount: Int) {
        this.userName = userName
        userColor = Colors.colorRgbFromHashCode(userName)
        userInformationBackground.styleClass.add("background")
        userInformationLabel.textProperty().apply {
            if (userName != null) set(userName)
            else bind(StringPropertyManager.createBinding("userInformationServer"))
        }
        userInformationColorSquare.style = "-fx-background-color: ${Colors.toString(userColor)};"
        userInformationText.textProperty().bind(StringPropertyManager.createBinding {
            val messageFormat = MessageFormat(StringPropertyManager[
                    "userInformationText"+(if (userName == null) "Server" else "")
            ])
            messageFormat.locale = StringPropertyManager.locale
            messageFormat.format(arrayOf(
                Colors.toString(userColor),
                userName,
                userMarinesCount,
                CustomChoiceFormat.formatByRules {
                    (userMarinesCount % 100).let {
                        if (it % 100 in (11..14) || it % 10 > 4 || it % 10 == 0) {
                            StringPropertyManager["userInformationSpaceMarinesMany"]
                        } else if (it % 10 in (2..4)) {
                            StringPropertyManager["userInformationSpaceMarinesFew"]
                        } else {
                            StringPropertyManager["userInformationSpaceMarinesOne"]
                        }
                    }
                }
            ))
        })

    }
}