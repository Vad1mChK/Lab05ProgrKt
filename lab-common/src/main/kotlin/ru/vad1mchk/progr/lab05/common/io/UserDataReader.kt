package ru.vad1mchk.progr.lab05.common.io

import ru.vad1mchk.progr.lab05.common.datatypes.User
import java.io.Console

class UserDataReader(/*val console: Console = System.console(),*/ val printer: Printer) {
//    private fun readUserName(): String {
//        printer.printNewLine("Введите имя пользователя:")
//        return console.readLine()
//    }
//
//    private fun readPassword(): String {
//        printer.printNewLine("Введите пароль:")
//        return console.readPassword().concatToString()
//    }

    fun readUser(): User {
        return User(1, "", "")
    }
}