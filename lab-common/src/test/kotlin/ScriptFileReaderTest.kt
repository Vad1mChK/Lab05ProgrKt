import org.junit.jupiter.api.Test
import ru.vad1mchk.progr.lab05.common.io.ScriptFileReader

object ScriptFileReaderTest {
    @Test
    fun scriptFileReader_whenReadingFile_ShouldReadAllRequestsAndElements() {
        val scriptFileReader = ScriptFileReader("../script")

        scriptFileReader.readAll()
    }
}