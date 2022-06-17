package ru.vad1mchk.progr.lab05.server.database

import ru.vad1mchk.progr.lab05.common.datatypes.MeleeWeapon
import java.sql.Connection
import java.sql.SQLException

class DatabaseInitializer(private val connector: DatabaseConnector) {
    private fun <T : Enum<T>> addConstantsToTable(connection: Connection, query: String, rows: Array<T>) {
        rows.iterator().forEach { row ->
            connection.prepareStatement(query).use { statement ->
                statement.setString(1, row.toString())
                statement.execute()
            }
        }
    }

    fun initialize(): Boolean {
        val queries = listOf(
            DatabaseQuery.CREATE_CHAPTER_TABLE,
            DatabaseQuery.CREATE_COORDINATES_TABLE,
            DatabaseQuery.CREATE_MELEE_WEAPON_TABLE,
            DatabaseQuery.CREATE_USER_TABLE,
            DatabaseQuery.CREATE_SPACE_MARINES_TABLE
        ).map { it.query }
        return connector.connect().use { connection ->
            try {
                for (query in queries) {
                    connection.prepareStatement(query).execute()
                }
                addConstantsToTable(
                    connection,
                    DatabaseQuery.INSERT_CONSTANT_INTO_MELEE_WEAPON.query,
                    MeleeWeapon.values()
                )
                true
            } catch (e: SQLException) {
                false
            }
        }
    }
}