package ru.vad1mchk.progr.lab05.client.util

import ru.vad1mchk.progr.lab05.client.application.MainApplication
import ru.vad1mchk.progr.lab05.client.controllers.MainApplicationController
import ru.vad1mchk.progr.lab05.common.datatypes.User

object Configuration {
    var user: User? = null
    var spaceMarinesCreated: UShort = 0u
    lateinit var mainApplication: MainApplication
}