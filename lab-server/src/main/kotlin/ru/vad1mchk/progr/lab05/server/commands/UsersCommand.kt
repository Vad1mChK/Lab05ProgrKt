package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.exceptions.DatabaseException
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.server.database.DatabaseConnector
import ru.vad1mchk.progr.lab05.server.database.DatabaseNegotiator
import ru.vad1mchk.progr.lab05.server.security.Sha1PasswordHasher

class UsersCommand : AbstractCommand(
    "users",
    "Выводит список имён всех зарегистрированных пользователей.",
    null,
    FOR_SERVER
) {
    override fun invoke(request: Request): Response {
        val database = DatabaseNegotiator(Sha1PasswordHasher(), DatabaseConnector())
        val message = try {
            database
                .selectAllUserNames()
                .map { it.toString() }
                .joinToString("\n")
        } catch (e: DatabaseException) {
            Printer().formatError(e)
        }
        return Response(database.selectAllUserNames().joinToString("\n"))
    }
}