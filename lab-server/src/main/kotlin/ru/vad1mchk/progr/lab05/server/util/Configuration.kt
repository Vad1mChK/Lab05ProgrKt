package ru.vad1mchk.progr.lab05.server.util

import ru.vad1mchk.progr.lab05.server.collection.SpaceMarineCollectionManager
import ru.vad1mchk.progr.lab05.server.commander.CommandInvoker

/**
 * Object for configuration of global properties of this application.
 */
object Configuration {
    @JvmStatic
    val COLLECTION_MANAGER = SpaceMarineCollectionManager()
    @JvmStatic
    val COMMAND_INVOKER = CommandInvoker()
    @JvmStatic
    lateinit var collectionFilePath: String
    @JvmStatic
    var isWorking = true
}