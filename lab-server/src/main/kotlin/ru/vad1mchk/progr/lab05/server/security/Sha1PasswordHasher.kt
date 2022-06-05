package ru.vad1mchk.progr.lab05.server.security

import java.security.MessageDigest

class Sha1PasswordHasher: PasswordHasher {
    val digest = MessageDigest.getInstance("SHA-1")

    override fun hash(password: String): ByteArray {
        return digest.digest(password.toByteArray())
    }

    override fun checkPassword(password: String, hashedPassword: ByteArray): Boolean {
        return hash(password).contentEquals(hashedPassword)
    }
}