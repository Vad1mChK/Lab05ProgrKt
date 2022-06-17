package ru.vad1mchk.progr.lab05.server.security

import java.security.MessageDigest
import java.security.SecureRandom

class Sha1PasswordHasher() : PasswordHasher {
    val digest = MessageDigest.getInstance("SHA-1")

    val secureRandom = SecureRandom()

    override fun hash(password: String): String {
        return bytesToHex(digest.digest(password.toByteArray()))
    }

    private fun bytesToHex(bytes: ByteArray): String {
        val charArray = CharArray(bytes.size shl 1)
        for (i in bytes.indices) {
            charArray[i*2] = ((bytes[i].toInt() and 0xff) / 16).toString(16)[0]
            charArray[i*2+1] = ((bytes[i].toInt() and 0xff) % 16).toString(16)[0]
        }
        return charArray.concatToString()
    }
}