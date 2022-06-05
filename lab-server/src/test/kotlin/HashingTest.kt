import org.junit.jupiter.api.Test
import ru.vad1mchk.progr.lab05.server.security.PasswordHasher
import ru.vad1mchk.progr.lab05.server.security.Sha1PasswordHasher

object HashingTest {
    @Test
    fun `Hash the password, see the result, then check if this was hashed correctly`() {
        val passwordHasher: PasswordHasher = Sha1PasswordHasher()

        val password = ("Протестую").also { println(it) }

        val hashedPassword = passwordHasher.hash(password).also { println(it) }

        assert(passwordHasher.checkPassword(password, hashedPassword))
    }
}