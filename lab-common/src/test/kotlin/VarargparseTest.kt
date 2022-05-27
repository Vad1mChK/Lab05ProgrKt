import org.junit.jupiter.api.Test
import ru.vad1mchk.progr.lab05.common.communication.EnteredCommand
import ru.vad1mchk.progr.lab05.common.util.Varargparse

object VarargparseTest {
    @Test
    fun testOne() {
        assert(
            Varargparse.splitString("some@string@split@by@ats", '@') ==
                    arrayListOf<String>("some", "string", "split", "by", "ats")
        )
    }

    @Test
    fun testTwo() {
        assert(
            Varargparse.splitString("This string \"will be escaped\"", ' ') ==
                    arrayListOf<String>("This", "string", "will be escaped")
        )
    }

    @Test
    fun testThree() {
        assert(
            Varargparse.splitString("This string \'will be escaped\' as\\ well") ==
                    arrayListOf<String>("This", "string", "will be escaped", "as well")
        )
    }

    @Test
    fun testFour() {
        assert(
            Varargparse.splitString("This is all done in \'Ivan Uskov\\\'s\' name") ==
                    arrayListOf<String>("This", "is", "all", "done", "in", "Ivan Uskov\'s", "name")
        )
    }

    @Test
    fun testFive() {
        assert(
            Varargparse.splitString("this,in\\,turn,is,a\\,working,example,of,splitting,\\\"CSV strings\\\"", ',')
                 == arrayListOf<String>(
                        "this",
                        "in,turn",
                        "is",
                        "a,working",
                        "example",
                        "of",
                        "splitting",
                        "\"CSV strings\""
            )
        )
    }

    @Test
    fun testSix() {
        val command = EnteredCommand.fromString(
            "Varargparse argument1 argument2 \"argument3 still argument3\""
        ).also { println(it) }
    }
}