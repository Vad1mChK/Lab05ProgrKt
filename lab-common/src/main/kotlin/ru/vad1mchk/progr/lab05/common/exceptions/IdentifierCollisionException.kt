package ru.vad1mchk.progr.lab05.common.exceptions

/**
 * Exception thrown whenever attempting to add collection element by an
 * already occupied ID, or when the collection itself is full and cannot
 * be filled further.
 */
class IdentifierCollisionException : CollectionException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}