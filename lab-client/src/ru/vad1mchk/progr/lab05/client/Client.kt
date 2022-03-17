package ru.vad1mchk.progr.lab05.client

import ru.vad1mchk.progr.lab05.client.commands.Clear
import ru.vad1mchk.progr.lab05.client.commands.Info
import ru.vad1mchk.progr.lab05.client.commands.Show
import ru.vad1mchk.progr.lab05.client.datatypes.Coordinates
import ru.vad1mchk.progr.lab05.client.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.client.manager.SpaceMarineCollectionManager
import ru.vad1mchk.progr.lab05.client.util.Deserializer
import java.nio.file.FileSystems
import java.time.LocalDate

fun main(args: Array<String>) {
    val path = FileSystems.getDefault().getPath("lab-client", "collection.csv")
    Deserializer.file = path.toFile()
    Deserializer.read()
    Clear().execute()
    SpaceMarineCollectionManager.addWithId(
        2007,
        SpaceMarine(
            "Apollo Justice",
            Coordinates(1111, 2222.0f),
            LocalDate.now(),
            0.1,
            3,
            true,
            null,
            null
        )
    )
    Info().execute()
    Show().execute()
}