package ru.vad1mchk.progr.lab05.common.application

import ru.vad1mchk.progr.lab05.common.io.Printer
import java.io.IOException
import java.nio.charset.Charset
import java.util.InputMismatchException
import java.util.Scanner
import kotlin.system.exitProcess

/**
 * Base class for client and server applications, defining some common behavior for both of them.
 */
abstract class AbstractApplication {
    protected val scanner = Scanner(System.`in`)

    abstract fun launch(args: Array<String>)

    protected fun readPort(): Int {
        var port: Int
        while (true) {
            Printer.printNoNewLine("Введите номер порта: ")
            try {
                port = scanner.nextInt()
                if (port !in (UShort.MIN_VALUE.toInt()..UShort.MAX_VALUE.toInt())) {
                    throw NumberFormatException()
                }
                return port
            } catch (e: Exception) {
                if (e is NumberFormatException || e is InputMismatchException || e is IOException) {
                    Printer.printError(
                        "Номер порта должен быть числом от ${UShort.MIN_VALUE} до ${UShort.MAX_VALUE
                        }. Пожалуйста, повторите попытку ввода."
                    )
                }
                else if (e is NoSuchElementException) {
                    exitProcess(0)
                }
            }
        }
    }
}