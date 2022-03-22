package ru.vad1mchk.progr.lab05.client.exceptions

/**
 * Exception thrown whenever the user has entered invalid data.
 */
class InvalidDataException : CollectionException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}