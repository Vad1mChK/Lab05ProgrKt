package ru.vad1mchk.progr.lab05.client.exceptions

/**
 * Base exception for all exceptions that have to do with the collection, its deserialization, etc.
 */
open class CollectionException : RuntimeException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}