package ru.vad1mchk.progr.lab05.client.datatypes

/**
 * Enum class to store different types of melee weapons
 * that may or may not be equipped by [SpaceMarine].
 */
enum class MeleeWeapon {
    CHAIN_SWORD,
    POWER_SWORD,
    CHAIN_AXE;

    companion object {
        fun listConstants(): String {
            return MeleeWeapon.values().joinToString(", ", "{", "}")
        }
    }
}