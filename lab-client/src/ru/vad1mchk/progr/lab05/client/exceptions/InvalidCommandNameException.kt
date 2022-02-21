package ru.vad1mchk.progr.lab05.client.exceptions

/**
 * Thrown when no command with such a name exists
 */
class InvalidCommandNameException : CommandException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}