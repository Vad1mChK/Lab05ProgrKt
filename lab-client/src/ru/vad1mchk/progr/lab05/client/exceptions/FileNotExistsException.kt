package ru.vad1mchk.progr.lab05.client.exceptions

/**
 * Exception thrown when the requested file does not exist.
 */
class FileNotExistsException : FileException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}