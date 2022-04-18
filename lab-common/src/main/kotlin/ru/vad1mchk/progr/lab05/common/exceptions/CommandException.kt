package ru.vad1mchk.progr.lab05.common.exceptions

/**
 * Base class for all exceptions that have to do with commands.
 */
open class CommandException : RuntimeException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}