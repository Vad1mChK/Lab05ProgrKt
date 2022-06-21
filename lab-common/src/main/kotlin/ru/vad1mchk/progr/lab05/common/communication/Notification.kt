package ru.vad1mchk.progr.lab05.common.communication

import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine

data class Notification(
    val notificationCode: Int,
    val spaceMarine: SpaceMarine? = null,
    val id: Int? = null
) {
    companion object {

    }
}
