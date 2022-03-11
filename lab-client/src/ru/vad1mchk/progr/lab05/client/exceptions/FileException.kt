package ru.vad1mchk.progr.lab05.client.exceptions

open class FileException : RuntimeException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
    constructor(message: String, cause: Throwable) : super(cause)
}