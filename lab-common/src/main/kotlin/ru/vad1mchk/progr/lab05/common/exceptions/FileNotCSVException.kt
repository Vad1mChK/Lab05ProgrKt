package ru.vad1mchk.progr.lab05.common.exceptions

/**
 * Exception thrown when the collection file does not have the .CSV extension.
 */
class FileNotCSVException : FileException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}