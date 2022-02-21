package ru.vad1mchk.progr.lab05.client.exceptions

/**
 * Thrown if there is an attempt to execute a script while reading the same one.
 * For safety purposes, it will be thrown even if you're attempting to
 * execute the script from another one, but it is planned to add the support
 * for multiple script execution later.
 */
class RecursiveScriptCallException: CommandException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}