package ru.vad1mchk.progr.lab05.common.datatypes

import java.io.Serializable

/**
 * Enum class to store different types of melee weapons that may or may not be equipped by [SpaceMarine].
 */
enum class MeleeWeapon: Serializable {
    CHAIN_SWORD,
    POWER_SWORD,
    CHAIN_AXE;

    companion object {
        private val serialVersionUID = 42069664L
        fun listConstants(): String {
            return values().joinToString(", ", "{", "}")
        }
    }
}