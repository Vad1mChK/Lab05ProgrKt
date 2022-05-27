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
    companion object {
        val MIN_PORT = UShort.MIN_VALUE.toInt()
        val MAX_PORT = UShort.MAX_VALUE.toInt()
    }

    protected abstract var scanner: Scanner

    abstract fun launch(args: Array<String>)

    protected fun readPort(): Int {
        var port: Int
        while (true) {
            Printer.printNewLine("Введите номер порта: ")
            try {
                port = scanner.nextInt()
                if (port > MAX_PORT || port < MIN_PORT) {
                    throw NumberFormatException()
                }
                break
            } catch (e: NumberFormatException) {
                Printer.printError("Номер порта должен быть числом от $MIN_PORT до $MAX_PORT. Повторите попытку ввода.")
            } catch (e: InputMismatchException) {
                Printer.printError("Номер порта должен быть числом. Повторите попытку ввода.")
            } catch (e: NoSuchElementException) {
                exitProcess(0)
            }
            scanner.nextLine()
        }
        return port
    }
}