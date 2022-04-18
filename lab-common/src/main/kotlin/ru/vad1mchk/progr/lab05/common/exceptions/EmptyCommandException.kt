package ru.vad1mchk.progr.lab05.common.exceptions

/**
 * Exception thrown when the command string is empty.
 */
class EmptyCommandException: CommandException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}