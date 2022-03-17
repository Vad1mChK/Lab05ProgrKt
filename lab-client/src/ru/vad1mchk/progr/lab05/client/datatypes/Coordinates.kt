package ru.vad1mchk.progr.lab05.client.datatypes

import ru.vad1mchk.progr.lab05.client.exceptions.ValidationException

data class Coordinates(val x: Int, val y: Float) : Validated {
    companion object {
        const val MIN_X: Int = -817
    }

    init {
        this.validate()
    }

    override fun validate() {
        if (x <= MIN_X) throw ValidationException("x must be greater than $MIN_X")
    }

    override fun toString(): String {
        return "($x, $y)"
    }
}
