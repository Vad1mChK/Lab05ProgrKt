package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.exceptions.DatabaseException
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.server.database.DatabaseNegotiator

class LoginCommand(
    val negotiator: DatabaseNegotiator,
    val printer: Printer
) : AbstractCommand(
    "login",
    "Авторизует пользователя в приложении.",
    null,
    FOR_LOGGED_OUT_CLIENT
) {
    override fun invoke(request: Request): Response? {
        val user = request.user!!
        return try {
            negotiator.checkUser(user)
            Response("Вы успешно вошли в приложение.", user = user)
        } catch (e: DatabaseException) {
            Response(printer.formatError(e))
        }
    }
}