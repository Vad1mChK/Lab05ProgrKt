package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
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
    override fun invoke(request: Request): Response {
        val user = request.user!!
        user.id = negotiator.checkUser(user).also {
            if (it == 0) return Response(printer.formatError("Пользователь с таким именем и паролем не найден."))
        }
        return Response("Вы успешно вошли в приложение.", user = user)
    }
}