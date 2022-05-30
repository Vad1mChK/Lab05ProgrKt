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

    /**
     * Launches the application using the specified arguments.
     * @param args Arguments of the application, usually passed from the main entry point.
     */
    abstract fun launch(args: Array<String>)

    /**
     * Reads the port number from the standard input. Loops until a valid port number is entered.
     * @return The entered port number.
     */
    protected fun readPort(): Int {
        var port: Int
        while (true) {
            Printer.printNewLine("Введите номер порта: ")
            try {
                port = scanner.nextInt()
                if (port !in MIN_PORT..MAX_PORT) {
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