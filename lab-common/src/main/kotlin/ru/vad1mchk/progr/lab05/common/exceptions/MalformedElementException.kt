package ru.vad1mchk.progr.lab05.common.exceptions

/**
 * Exception thrown when the entry in the CSV file has a wrong number of fields.
 */
class MalformedElementException : CollectionException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}