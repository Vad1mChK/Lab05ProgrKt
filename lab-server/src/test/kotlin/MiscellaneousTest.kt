import org.junit.jupiter.api.Test
import ru.vad1mchk.progr.lab05.common.communication.Request
import ru.vad1mchk.progr.lab05.common.datatypes.Coordinates
import ru.vad1mchk.progr.lab05.common.datatypes.MeleeWeapon
import ru.vad1mchk.progr.lab05.common.datatypes.SpaceMarine
import ru.vad1mchk.progr.lab05.common.io.Printer
import ru.vad1mchk.progr.lab05.server.commands.AddCommand
import ru.vad1mchk.progr.lab05.server.commands.HelpCommand
import ru.vad1mchk.progr.lab05.server.commands.InfoCommand
import ru.vad1mchk.progr.lab05.server.commands.ShowCommand
import java.time.LocalDate

object MiscellaneousTest {
    @Test
    fun testOne() {
        Printer.inviteInput(false, "John Doe")
        println("some command")
        Printer.inviteInput(false, "Jane Doe")
        println("some other command")
        Printer.inviteInput(true)
        println("some command")
    }

    @Test
    fun testTwo() {
        val helpCommand = HelpCommand()
        InfoCommand()
        ShowCommand()
        AddCommand()(Request("add", SpaceMarine(
            44,
            "Саймон Блэккуилл",
            Coordinates(1,2.0f),
            LocalDate.now(),
            0.01,
            3,
            false,
            MeleeWeapon.CHAIN_SWORD,
            null
        )))
        println(helpCommand(Request("help")).stringMessage)
    }
}