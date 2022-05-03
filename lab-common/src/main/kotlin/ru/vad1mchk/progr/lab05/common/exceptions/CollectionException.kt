package ru.vad1mchk.progr.lab05.common.exceptions

/**
 * Base exception for all exceptions that have to do with the collection, its deserialization, etc.
 */
@Suppress("unused")
open class CollectionException : Exception {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}