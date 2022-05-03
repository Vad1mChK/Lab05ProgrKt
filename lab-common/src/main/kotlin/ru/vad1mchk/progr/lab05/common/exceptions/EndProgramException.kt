package ru.vad1mchk.progr.lab05.common.exceptions

/**
 * Exception thrown whenever the program or the current script has to be stopped forcefully.
 */
@Suppress("unused")
class EndProgramException : RuntimeException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}