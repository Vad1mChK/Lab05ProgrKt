package ru.vad1mchk.progr.lab05.client.exceptions

/**
 * Thrown when empty command is passed
 */
class EmptyCommandException : CommandException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}