package ru.vad1mchk.progr.lab05.server.util

import ru.vad1mchk.progr.lab05.common.messages.StringResources
import ru.vad1mchk.progr.lab05.server.collection.SpaceMarineCollectionManager
import ru.vad1mchk.progr.lab05.server.commands.Commander
import java.io.File
import java.util.*

/**
 * Object to store current configuration of the server application.
 */
object Configuration {
    var isWorking = true
    lateinit var collectionFile: File
    var currentLocale: Locale = Locale.US
    val COLLECTION_MANAGER = SpaceMarineCollectionManager(currentLocale)
    val COMMANDER = Commander()
    val STRING_RESOURCES = StringResources()
}