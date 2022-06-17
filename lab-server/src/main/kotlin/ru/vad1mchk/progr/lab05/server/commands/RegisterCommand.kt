package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.exceptions.DatabaseException
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.common.io.UserDataReader
import ru.vad1mchk.progr.lab05.server.database.DatabaseNegotiator

class RegisterCommand(
    val negotiator: DatabaseNegotiator,
    private val printer: Printer
): AbstractCommand(
    "register",
    "Регистрирует нового пользователя.",
    null,
    FOR_LOGGED_OUT_CLIENT
) {
    override fun invoke(request: Request): Response? {
        val user = request.user!!
        return try {
            negotiator.insertUser(user)
            Response("Пользователь успешно зарегистрирован.", user = user)
        } catch (e: DatabaseException) {
            Response(printer.formatError(e))
        }
    }
}