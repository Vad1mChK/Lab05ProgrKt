package ru.vad1mchk.progr.lab05.common.exceptions

/**
 * Base class for exceptions that are connected to file operations.
 */
open class FileException : RuntimeException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}