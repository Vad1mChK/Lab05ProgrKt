package ru.vad1mchk.progr.lab05.server.commands

import ru.vad1mchk.progr.lab05.common.collection.CollectionManager
import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.communication.Response
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import java.time.format.DateTimeFormatter

class InfoCommand(
    private val collectionManager: CollectionManager<SpaceMarine>
) : AbstractCommand(
    "info",
    "Выводит основную информацию о коллекции.",
    null,
    FOR_SERVER_AND_LOGGED_IN_CLIENT
) {
    override fun invoke(request: Request): Response {
        return Response(
            """
                |Информация о коллекции:
                |   Тип коллекции: ${collectionManager.collection().javaClass.simpleName}
                |   Тип элементов: SpaceMarine
                |   Размер коллекции: ${collectionManager.collection().size} элем.
                |   Текущему пользователю принадлежат: ${
                collectionManager.collection().filter { it.creatorName == request.user?.userName }.size
            } элем.
                |   Дата инициализации: ${
                collectionManager.initializationDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            }.
            """.trimMargin()
        )
    }
}