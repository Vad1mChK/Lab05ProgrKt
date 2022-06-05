package ru.vad1mchk.progr.lab05.server.security

interface PasswordHasher {
    fun hash(password: String): ByteArray

    fun checkPassword(password: String, hashedPassword: ByteArray): Boolean
}