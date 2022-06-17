package ru.vad1mchk.progr.lab05.server.database

import java.sql.Connection
import java.sql.DriverManager

class DatabaseConnector() {
    companion object {
        lateinit var hostName: String
            private set
        lateinit var databaseName: String
            private set
        lateinit var userName: String
            private set
        lateinit var password: String
            private set

        fun setVariables(hostName: String, databaseName: String, userName: String, password: String) {
            this.hostName = hostName
            this.databaseName = databaseName
            this.userName = userName
            this.password = password
        }
    }

    fun connect(): Connection {
        return DriverManager.getConnection(
            "jdbc:postgresql://$hostName:5432/$databaseName", userName, password
        )
    }
}