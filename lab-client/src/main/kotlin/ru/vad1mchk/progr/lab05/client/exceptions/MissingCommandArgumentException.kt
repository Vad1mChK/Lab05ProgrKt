package ru.vad1mchk.progr.lab05.client.exceptions

/**
 * Exception thrown when the command requires an argument but none is given.
 */
class MissingCommandArgumentException : CommandException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}