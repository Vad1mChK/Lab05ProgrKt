package ru.vad1mchk.progr.lab05.client.exceptions

/**
 * Thrown when the program is executed with no arguments specified
 */
class EmptyProgramArgumentException : ProgramArgumentException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}