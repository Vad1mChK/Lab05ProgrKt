package ru.vad1mchk.progr.lab05.common.util

/**
 * Utility class to split the entered strings or parse various data
 * types from strings.
 */
object Varargparse {
    @JvmStatic
            /**
             * Splits a string into a list of strings using
             * [delimiters]. Do not use single quotes `'''`, double
             * quotes `'"'` or backslashes `'\'` as delimiters.
             *
             * @param splittableString String to split.
             * @param delimiters Delimiters that are removed while
             *     splitting if not escaped.
             * @return List of strings.
             */
    fun splitString(splittableString: String, vararg delimiters: Char = charArrayOf(' ')): ArrayList<String> {
        val listString = mutableListOf<String>()
        var isSingleQuoteEscaping = false
        var isDoubleQuoteEscaping = false
        var isSlashEscaping = false

        val currentString = StringBuilder()
        for (symbol in splittableString) {
            when (symbol) {
                in delimiters -> {
                    if (!isSingleQuoteEscaping && !isDoubleQuoteEscaping && !isSlashEscaping) {
                        listString.add(currentString.toString())
                        currentString.clear()
                    } else {
                        currentString.append(symbol)
                    }
                }
                '\\' -> {
                    if (!isSlashEscaping) {
                        isSlashEscaping = true
                    } else {
                        isSlashEscaping = false
                        currentString.append(symbol)
                    }
                }
                '"' -> {
                    if (isSlashEscaping) {
                        currentString.append(symbol)
                        isSlashEscaping = false
                    } else {
                        if (!isSingleQuoteEscaping && !isDoubleQuoteEscaping) {
                            isDoubleQuoteEscaping = true
                        } else if (isSingleQuoteEscaping) {
                            currentString.append(symbol)
                        } else {
                            isDoubleQuoteEscaping = false
                        }
                    }
                }
                '\'' -> {
                    if (isSlashEscaping) {
                        currentString.append(symbol)
                        isSlashEscaping = false
                    } else {
                        if (!isSingleQuoteEscaping && !isDoubleQuoteEscaping) {
                            isSingleQuoteEscaping = true
                        } else if (!isSingleQuoteEscaping) {
                            currentString.append(symbol)
                        } else {
                            isSingleQuoteEscaping = false
                        }
                    }
                }
                else -> {
                    currentString.append(symbol)
                    isSlashEscaping = false
                }
            }
        }
        listString.add(currentString.toString())
        return ArrayList(listString.toList())
    }
}