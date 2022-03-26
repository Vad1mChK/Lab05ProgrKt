package ru.vad1mchk.progr.lab05.client.exceptions

/**
 * Exception thrown when a new file cannot be created.
 */
class FileCannotCreateException : FileException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}