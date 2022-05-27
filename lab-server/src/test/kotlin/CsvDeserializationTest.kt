import org.junit.jupiter.api.Test
import ru.vad1mchk.progr.lab05.server.csv.CsvDeserializer
import ru.vad1mchk.progr.lab05.server.util.Configuration
import java.io.File

object CsvDeserializationTest {
    @Test
    fun testOne() {
        val csvDeserializer = CsvDeserializer("space_marines.csv")
        csvDeserializer.readAll()
        Configuration.COLLECTION_MANAGER.stream().forEach { println(it) }
        println(Configuration.COLLECTION_MANAGER.info())
    }
}