package ru.vad1mchk.progr.lab05.client.exceptions

/**
 * Thrown when data doesn't satisfy the requirements
 * (e.g. Chapter.marinesNumber is not in (0; 1000] range)
 */
class InvalidDataValueException : CommandException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
}