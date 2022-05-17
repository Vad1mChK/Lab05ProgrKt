package ru.vad1mchk.progr.lab05.common.communication

import java.io.Serializable

data class Request(
    val stringMessage: String
) : Serializable {
    companion object {
        const val serialVersionUID = 2_147_483_647L
    }
}