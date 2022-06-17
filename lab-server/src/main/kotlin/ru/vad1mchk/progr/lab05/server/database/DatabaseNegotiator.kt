package ru.vad1mchk.progr.lab05.server.database

import ru.vad1mchk.progr.lab05.common.datatypes.*
import ru.vad1mchk.progr.lab05.common.exceptions.DatabaseException
import ru.vad1mchk.progr.lab05.server.security.PasswordHasher
import java.sql.*
import java.sql.Date
import java.util.*

class DatabaseNegotiator(
    private val passwordHasher: PasswordHasher,
    private val connector: DatabaseConnector
) {
    @Synchronized
    fun insertUser(user: User): Int {
        connector.connect().use { connection ->
            try {
                val statement = connection.prepareStatement(DatabaseQuery.INSERT_USER.query)
                statement.apply {
                    setString(1, user.userName)
                    setString(2, passwordHasher.hash(user.password))
                }
                val resultSet = statement.executeQuery()
                resultSet.next()
                return resultSet.getInt("id").also { user.id = it }
            } catch (e: SQLException) {
                e.printStackTrace()
                throw DatabaseException(
                    "Не удалось добавить пользователя.",
                    e
                )
            } finally {
                connection.close()
            }
        }
    }

    @Synchronized
    fun checkUser(user: User): Int {
        connector.connect().use { connection ->
            try {
                val statement = connection.prepareStatement(DatabaseQuery.SELECT_USER.query)
                statement.apply {
                    setString(1, user.userName)
                    setString(2, passwordHasher.hash(user.password))
                    statement.executeQuery().also {
                        if (!it.next()) throw DatabaseException("Пользователь не существует или пароль введён неверно.")
                        return it.getInt("id")
                    }
                }
            } catch (e: SQLException) {
                throw DatabaseException("Не удалось найти пользователя.", e)
            } finally {
                connection.close()
            }
        }
    }

    @Synchronized
    fun selectAllUserNames(): ArrayList<String> {
        val listOfUserNames = ArrayList<String>()
        connector.connect().use { connection ->
            try {
                val statement = connection.prepareStatement(DatabaseQuery.SELECT_ALL_USER_NAMES.query)
                val resultSet = statement.executeQuery()
                while (resultSet.next()) {
                    listOfUserNames.add(resultSet.getString("user_name"))
                }
            } catch (e: SQLException) {
                throw DatabaseException("Не удалось получить имена пользователей.", e)
            } finally {
                connection.close()
            }
        }
        return listOfUserNames
    }

    @Synchronized
    fun insertSpaceMarine(spaceMarine: SpaceMarine, user: User?): Int {
        spaceMarine.creatorName = user?.userName
        connector.connect().use { connection ->
            try {
                val statement = connection.prepareStatement(
                    (if (spaceMarine.chapter != null) {
                        DatabaseQuery.INSERT_SPACE_MARINE_WITH_CHAPTER
                    } else {
                        DatabaseQuery.INSERT_SPACE_MARINE_WITHOUT_CHAPTER
                    }).query
                )
                fillStatementWithSpaceMarineData(spaceMarine, true, user, statement)
                val resultSet = statement.executeQuery()
                resultSet.next()
                return resultSet.getInt("id").also { spaceMarine.id = it }
            } catch (e: SQLException) {
                throw DatabaseException("Не удалось добавить космодесантника.", e)
            } finally {
                connection.close()
            }
        }
    }

    @Synchronized
    fun updateSpaceMarine(spaceMarine: SpaceMarine): Int {
        connector.connect().use { connection ->
            try {
                val statement = connection.prepareStatement(
                    (if (spaceMarine.chapter != null) {
                        DatabaseQuery.UPDATE_SPACE_MARINE_WITH_CHAPTER
                    } else {
                        DatabaseQuery.UPDATE_SPACE_MARINE_WITHOUT_CHAPTER
                    }).query
                )
                var counter = fillStatementWithSpaceMarineData(spaceMarine, false, null, statement)
                statement.setInt(++counter, spaceMarine.id)
                statement.executeUpdate()
                return spaceMarine.id
            } catch (e: SQLException) {
                e.printStackTrace()
                throw DatabaseException("Не удалось обновить космодесантника.", e)
            } finally {
                connection.close()
            }
        }
    }

    @Synchronized
    fun deleteSpaceMarineById(id: Int): Int {
        connector.connect().use { connection ->
            try {
                val statement = connection.prepareStatement(DatabaseQuery.DELETE_SPACE_MARINE_BY_ID.query)
                statement.setInt(1, id)
                return statement.executeUpdate()
            } catch (e: SQLException) {
                throw DatabaseException("Не удалось удалить космодесантника.", e)
            } finally {
                connection.close()
            }
        }
    }

    @Synchronized
    fun deleteSpaceMarinesByUser(user: User?): Int {
        val isUserNotNull = user != null
        connector.connect().use { connection ->
            try {
                val statement = connection.prepareStatement(
                    (if (isUserNotNull) {
                        DatabaseQuery.DELETE_SPACE_MARINES_BY_CREATOR
                    } else {
                        DatabaseQuery.DELETE_ALL_SPACE_MARINES
                    }).query
                )
                if (isUserNotNull) {
                    statement.setInt(1, user!!.id)
                }
                return statement.executeUpdate()
            } catch (e: SQLException) {
                throw DatabaseException(
                    "Не удалось удалить " + (if (isUserNotNull) {
                        "космодесантников, принадлежащих пользователю ${user!!.userName}."
                    } else {
                        "всех космодесантников."
                    }), e
                )
            }
        }
    }

    @Synchronized
    fun selectAllSpaceMarines(): List<SpaceMarine> {
        val listOfSpaceMarines = Collections.synchronizedList(ArrayList<SpaceMarine>())
        connector.connect().use { connection ->
            try {
                val statement = connection.prepareStatement(DatabaseQuery.SELECT_ALL_SPACE_MARINES.query)
                val resultSet = statement.executeQuery()
                while (resultSet.next()) {
                    listOfSpaceMarines.add(parseSpaceMarine(resultSet))
                }
            } catch (e: SQLException) {
                throw DatabaseException("Не удалось получить всех космодесантников.", e)
            } finally {
                connection.close()
            }
        }
        return listOfSpaceMarines
    }

    private fun parseSpaceMarine(resultSet: ResultSet): SpaceMarine {
        var counter = 0
        return SpaceMarine(
            resultSet.getInt(++counter),
            resultSet.getString(++counter),
            resultSet.getString(++counter),
            Coordinates(resultSet.getInt(++counter), resultSet.getFloat(++counter)),
            resultSet.getDate(++counter).toLocalDate(),
            resultSet.getDouble(++counter),
            resultSet.getLong(++counter),
            resultSet.getBoolean(++counter),
            resultSet.getString(++counter)?.let { MeleeWeapon.valueOf(it) },
            resultSet.getString(++counter)?.let {
                Chapter(
                    it,
                    resultSet.getString(++counter),
                    resultSet.getInt(++counter)
                )
            }
        )
    }

    private fun fillStatementWithSpaceMarineData(
        spaceMarine: SpaceMarine, addUserAndDate: Boolean, userToAdd: User?, statement: PreparedStatement
    ): Int {
        var counter = 0
        statement.apply {
            counter = addCoordinatesInfo(counter, spaceMarine, statement)
            counter = addChapterInfoIfNotNull(counter, spaceMarine, statement)
            if (addUserAndDate) {
                userToAdd?.let { setInt(++counter, it.id) } ?: setNull(++counter, Types.INTEGER)
            }
            setString(++counter, spaceMarine.name)
            counter = addCoordinatesInfo(counter, spaceMarine, statement)
            if (addUserAndDate) {
                setDate(++counter, Date.valueOf(spaceMarine.creationDate))
            }
            setDouble(++counter, spaceMarine.health)
            setLong(++counter, spaceMarine.heartCount)
            setBoolean(++counter, spaceMarine.loyal)
            setString(++counter, spaceMarine.meleeWeapon?.name)
            counter = addChapterInfoIfNotNull(counter, spaceMarine, statement)
        }
        return counter
    }

    private fun addCoordinatesInfo(oldCounter: Int, spaceMarine: SpaceMarine, statement: PreparedStatement): Int {
        var counter = oldCounter
        statement.setInt(++counter, spaceMarine.coordinates.x)
        statement.setFloat(++counter, spaceMarine.coordinates.y)
        return counter
    }

    private fun addChapterInfoIfNotNull(oldCounter: Int, spaceMarine: SpaceMarine, statement: PreparedStatement): Int {
        (spaceMarine.chapter != null).also {
            if (!it) return oldCounter
        }
        var counter = oldCounter
        statement.setString(++counter, spaceMarine.chapter!!.name)
        statement.setString(++counter, spaceMarine.chapter!!.parentLegion)
        statement.setInt(++counter, spaceMarine.chapter!!.marinesCount)
        return counter
    }
}