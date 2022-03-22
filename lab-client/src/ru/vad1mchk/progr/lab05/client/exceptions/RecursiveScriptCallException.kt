package ru.vad1mchk.progr.lab05.client.exceptions

/**
 * Exception thrown when attempting to call a script
 * that is already open whilst reading another script.
 */
class RecursiveScriptCallException : CommandException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}