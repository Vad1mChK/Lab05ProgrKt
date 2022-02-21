package ru.vad1mchk.progr.lab05.client.exceptions

/**
 * This is an error that should never be caught.
 * In case it is thrown, it indicates that something is profoundly
 * wrong with the program, and it should be fixed immediately.
 */
class InternalError : Error {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}