package ru.vad1mchk.progr.lab05.client.datatypes

/**
 * Types of weapons used in melee combat by SpaceMarines
 */
enum class MeleeWeapon : Comparator<MeleeWeapon?> {
    /**
     * A sword with powered teeth that run along a single-edged blade like that of a chainsaw
     */
    CHAIN_SWORD,

    /**
     * A Power Weapon shaped into a sword of varying lengths and designs crafted from one of any number of different materials
     */
    POWER_SWORD,

    /**
     * A brutal hand-to-hand chain weapon crafted in the form of a great one or two-handed axe which incorporates a powered chainsaw edge
     */
    CHAIN_AXE;

    override fun compare(left: MeleeWeapon?, right: MeleeWeapon?): Int {
        return if (left == null && right == null) {
            0
        } else if (left == null) {
            -1
        } else if (right == null) {
            1
        } else {
            Integer.compare(left.ordinal, right.ordinal)
        }
    }
}