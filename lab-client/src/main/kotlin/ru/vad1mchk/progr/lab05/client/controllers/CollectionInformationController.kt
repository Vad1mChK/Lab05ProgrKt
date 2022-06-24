package ru.vad1mchk.progr.lab05.client.controllers

import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.text.Text
import ru.vad1mchk.progr.lab05.client.strings.StringPropertyManager
import ru.vad1mchk.progr.lab05.client.util.FlatSpaceMarine
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import tornadofx.Controller
import java.text.MessageFormat
import java.sql.Date
import java.time.LocalDate
import java.util.*

class CollectionInformationController: Controller() {
    @FXML
    lateinit var collectionInformationLabel: Label
    @FXML
    lateinit var collectionInformationText: Text

    fun initialize(collection: MutableCollection<FlatSpaceMarine>, userName: String) {
        collectionInformationLabel.textProperty().bind(
            StringPropertyManager.createBinding("collectionInformationLabel")
        )
        collectionInformationText.textProperty().bind(
            StringPropertyManager.createBinding {
                val messageFormat = MessageFormat("collectionInformationText")
                messageFormat.locale = StringPropertyManager.locale
                messageFormat.format(arrayOf(
                    LinkedList::class.java.simpleName,
                    SpaceMarine::class.java.simpleName,
                    collection.count { it.creatorName == userName },
                    collection.count(),
                    userName,
                    Date.valueOf(LocalDate.now())
                ))
            }
        )
    }
}