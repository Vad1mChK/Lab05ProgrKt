package ru.vad1mchk.progr.lab05.common.exceptions

/**
 * Exception thrown when attempting to replace or delete an element by a non-existent identifier.
 */
@Suppress("unused")
class IdentifierNotExistsException : CollectionException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}