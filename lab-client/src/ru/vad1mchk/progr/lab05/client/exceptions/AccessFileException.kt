package ru.vad1mchk.progr.lab05.client.exceptions

/**
 * Base class for every exception that could occur
 * while trying to open a collection or a script
 * file
 */
open class AccessFileException : RuntimeException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}