package ru.vad1mchk.progr.lab05.common.connection
import ru.vad1mchk.progr.lab05.common.io.OutputManager
import java.io.Serializable

class ResponseMessage(
    message: String,
    status: Status
): Serializable {
    val message: String
    public var status = Status.OKAY
    init {
        this.message = message
        this.status = status
    }

    fun sayInfo(message: String) = OutputManager.sayInfo(message)

    fun sayException(message: String) = apply {
        OutputManager.sayException(message)
        status = Status.EXCEPTION
    }

    fun sayException(e: Exception) = apply {
        OutputManager.sayException(e)
        status = Status.EXCEPTION
    }
}