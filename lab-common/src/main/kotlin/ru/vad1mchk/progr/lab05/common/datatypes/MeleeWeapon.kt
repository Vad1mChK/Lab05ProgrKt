package ru.vad1mchk.progr.lab05.common.datatypes

import java.io.Serializable

/**
 * Enum that represents the melee weapons that space marines may or may
 * not equip.
 */
enum class MeleeWeapon : Serializable {
    POWER_SWORD,
    CHAIN_SWORD,
    CHAIN_AXE;

    companion object {
        const val serialVersionUID = 1_073_741_823L
        fun listAllConstants(): String {
            return MeleeWeapon.values().joinToString(", ", "{", "}")
        }
    }
}