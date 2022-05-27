package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.exceptions.IdentifierCollisionException
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.server.util.Configuration

class AddIfMinCommand: AbstractCommand (
    "add_if_min",
    "Добавляет новый элемент в коллекцию, если его значение меньше, чем у всех элементов этой коллекции.",
    "{element}"
) {
    override fun invoke(request: Request): Response {
        return try {
            if (Configuration.COLLECTION_MANAGER.stream().anyMatch { it <= request.spaceMarineArgument!! }) {
                Response("Элемент не был добавлен: найдены элементы, которые меньше либо равны ему.")
            } else {
                Response("Элемент успешно добавлен.")
            }
        } catch (e: IdentifierCollisionException) {
            Response(Printer.formatError(e.message ?: ""))
        }
    }
}