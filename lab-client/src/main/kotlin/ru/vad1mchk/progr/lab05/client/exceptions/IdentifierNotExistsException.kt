package ru.vad1mchk.progr.lab05.client.exceptions

/**
 * Exception thrown when attempting to replace or delete an element by a non-existent identifier.
 */
class IdentifierNotExistsException : CollectionException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}