package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.exceptions.IdentifierCollisionException
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.server.util.Configuration

class AddCommand: AbstractCommand(
    "add",
    "Добавляет новый элемент в коллекцию.",
    "{element}"
) {
    override fun invoke(request: Request): Response {
        return try {
            Configuration.COLLECTION_MANAGER.add(request.spaceMarineArgument!!)
            Response("Элемент добавлен в коллекцию.")
        } catch (e: IdentifierCollisionException) {
            Response(Printer.formatError(e.message ?: ""))
        }
    }
}