package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.collection.CollectionManager
import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.common.exceptions.CollectionException
import ru.vad1mchk.progr.lab05.common.exceptions.DatabaseException
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.server.database.DatabaseNegotiator

class UpdateCommand(
    private val collectionManager: CollectionManager<SpaceMarine>,
    private val negotiator: DatabaseNegotiator,
    val printer: Printer
): AbstractCommand(
    "update",
    "Обновляет значение элемента в коллекции, id которого равен заданному.",
    "id {element}",
    FOR_SERVER_AND_LOGGED_IN_CLIENT
) {
    override fun invoke(request: Request): Response? {
        val spaceMarine = request.spaceMarineArgument!!
        spaceMarine.id = request.idArgument!!
        request.user?.let {
            if(it.userName != spaceMarine.name) {
                return Response(
                    printer.formatError("Невозможно обновить элемент: он принадлежит другому пользователю.")
                )
            }
        }
        return try {
            negotiator.updateSpaceMarine(spaceMarine)
            collectionManager.update(spaceMarine.id, spaceMarine)
            Response("Элемент успешно добавлен в коллекцию.")
        } catch (e: DatabaseException) {
            return Response(printer.formatError(e))
        } catch (e: CollectionException) {
            return Response(printer.formatError(e))
        }
    }
}