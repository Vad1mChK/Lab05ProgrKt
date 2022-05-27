import org.junit.jupiter.api.Test
import java.nio.charset.Charset

object OtherTest {
    @Test
    fun javaEncoding_whenReadingFromCli_mustNotBeUtf8() {
        val charset = Charset.defaultCharset()

        assert(charset != Charset.forName("utf-8"))
    }
}