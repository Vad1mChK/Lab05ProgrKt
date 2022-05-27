package ru.vad1mchk.progr.lab05.common.exceptions

/**
 * Exception thrown when attempting to access a collection element by a
 * nonexistent ID.
 */
class IdentifierNotExistsException : CollectionException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}