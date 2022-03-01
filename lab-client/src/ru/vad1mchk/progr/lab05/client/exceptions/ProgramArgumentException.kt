package ru.vad1mchk.progr.lab05.client.exceptions

/**
 * Base class for exceptions that are related to the program arguments.
 * These exceptions are only thrown and caught at the beginning
 * and they stop the execution of the program
 */
open class ProgramArgumentException : RuntimeException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}
