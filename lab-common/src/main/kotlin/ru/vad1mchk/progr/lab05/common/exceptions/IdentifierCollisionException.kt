package ru.vad1mchk.progr.lab05.common.exceptions

/**
 * Exception thrown when attempting to add a new element by the already existing identifier.
 */
@Suppress("unused")
class IdentifierCollisionException : CollectionException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}