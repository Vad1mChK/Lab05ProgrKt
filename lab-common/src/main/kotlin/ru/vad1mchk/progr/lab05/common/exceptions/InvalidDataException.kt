package ru.vad1mchk.progr.lab05.common.exceptions

/**
 * Exception thrown whenever the user has entered invalid data.
 */
@Suppress("unused")
class InvalidDataException : Exception {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}