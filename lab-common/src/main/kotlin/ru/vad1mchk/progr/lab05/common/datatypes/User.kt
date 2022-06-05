package ru.vad1mchk.progr.lab05.common.datatypes

import java.io.Serializable

data class User(
    val id: Int,
    val userName: String,
    val password: String?
): Serializable {
    companion object {
        const val serialVersionUID = 0xdefacedL
    }
}