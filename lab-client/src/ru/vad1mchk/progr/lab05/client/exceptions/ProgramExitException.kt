package ru.vad1mchk.progr.lab05.client.exceptions

/**
 * Thrown when command "exit" is received. Signalizes that
 * it's time to exit program
 */
class ProgramExitException: RuntimeException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}