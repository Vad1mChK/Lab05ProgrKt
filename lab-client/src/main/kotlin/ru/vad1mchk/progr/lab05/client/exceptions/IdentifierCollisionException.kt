package ru.vad1mchk.progr.lab05.client.exceptions

/**
 * Exception thrown when attempting to add a new element by the already existing identifier.
 */
class IdentifierCollisionException : CollectionException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}