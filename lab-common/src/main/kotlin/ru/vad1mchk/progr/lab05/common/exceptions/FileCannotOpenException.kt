package ru.vad1mchk.progr.lab05.common.exceptions

/**
 * Exception thrown when the file cannot be opened.
 */
class FileCannotOpenException : FileException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}