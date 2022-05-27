import jdk.nashorn.internal.ir.annotations.Ignore
import org.junit.jupiter.api.Test
import ru.vad1mchk.progr.lab05.common.datatypes.Chapter
import ru.vad1mchk.progr.lab05.common.datatypes.Coordinates
import ru.vad1mchk.progr.lab05.common.datatypes.MeleeWeapon
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.server.csv.CsvSerializer
import ru.vad1mchk.progr.lab05.server.util.Configuration
import java.io.File
import java.time.LocalDate

object CsvSerializationTest {
    @Test
    @Ignore
    fun testOne() {
        val csvSerializer = CsvSerializer("space_marines.csv")
        Configuration.COLLECTION_MANAGER.add(
            SpaceMarine(
                1,
                "Ши-Лун Лан",
                Coordinates(59, 30.0f),
                LocalDate.of(1992, 5, 2),
                10.1,
                2,
                true,
                MeleeWeapon.POWER_SWORD,
                Chapter("Дело о похищении", "AAI", 8)
            )
        )
        csvSerializer.writeAllToFile()
    }
}