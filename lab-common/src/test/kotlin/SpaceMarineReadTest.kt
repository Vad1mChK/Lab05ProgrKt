import org.junit.jupiter.api.Test
import ru.vad1mchk.progr.lab05.common.io.SpaceMarineDataReader
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.util.*


object SpaceMarineReadTest {
    @Test
    fun testOne() {
        val sysInBackup: InputStream = System.`in`
        // I had to redirect the input because, for some reason, System.`in` behaves weirdly in tests
        val input = ByteArrayInputStream("""
            |Аполло Джастис
            |-888
            |-817
            |-816
            |837
            |1.0
            |3
            |wtf
            |true
            |WHIP
            |
            |Козырное дело
            |AJ: AA
            |3
        """.trimMargin().toByteArray())
        System.setIn(input)
        val spaceMarine = SpaceMarineDataReader(Scanner(System.`in`,"utf-8")).readMarine()
        println(spaceMarine)
        System.setIn(sysInBackup)
    }
}