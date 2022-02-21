package ru.vad1mchk.progr.lab05.client.exceptions

/**
 * Thrown when arguments of this program are not empty, but there is still
 * something wrong with them (e.g. more than 1 argument is passed, or
 * the collection file is not in csv format)
 */
class InvalidProgramArgumentException: ProgramArgumentException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}