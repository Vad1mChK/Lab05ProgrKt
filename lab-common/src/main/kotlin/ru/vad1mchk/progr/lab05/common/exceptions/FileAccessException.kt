package ru.vad1mchk.progr.lab05.common.exceptions

/**
 * Exception thrown when file doesn't have enough access permissions to read or write.
 */
class FileAccessException : FileException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}