package ru.vad1mchk.progr.lab05.common.connection

/**
 * Possible statuses of the execution of the program.
 */
enum class Status {
    /**
     * This status signifies that something is wrong.
     */
    EXCEPTION,
    /**
     * This status signifies that the program needs to be terminated.
     */
    EXIT,
    /**
     * This status signifies that the execution is going fine.
     */
    OKAY
}