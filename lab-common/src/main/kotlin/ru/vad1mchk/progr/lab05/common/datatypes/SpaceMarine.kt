package ru.vad1mchk.progr.lab05.common.datatypes

import ru.vad1mchk.progr.lab05.common.exceptions.InvalidDataException
import java.io.Serializable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Data class that represents a space marine.
 *
 * @property id Unique ID of this space marine, generated automatically.
 * @property creatorName Name of the creator of this space marine, [null] for space marines created by the server.
 * @property name Name of the space marine.
 * @property coordinates Coordinates of the space marine.
 * @property creationDate Creation date of this space marine,
 *     automatically generated.
 * @property health Health of this space marine.
 * @property heartCount Heart count of this space marine.
 * @property loyal Whether the space marine is loyal.
 * @property meleeWeapon Melee weapon of this space marine (might be
 *     null).
 * @property chapter Chapter this space marine appears in.
 */

data class SpaceMarine(
    var id: Int = 1,
    var creatorName: String? = null,
    val name: String,
    val coordinates: Coordinates,
    val creationDate: LocalDate = LocalDate.now(),
    val health: Double,
    val heartCount: Long,
    val loyal: Boolean,
    val meleeWeapon: MeleeWeapon?,
    val chapter: Chapter?
) : Serializable, Comparable<SpaceMarine> {
    companion object {
        const val serialVersionUID = 1_073_741_820L
        const val MIN_HEALTH = 0.0
        const val MIN_HEART_COUNT = 1L
        const val MAX_HEART_COUNT = 3L
    }

    init {
        if (id <= 0) {
            throw InvalidDataException("Идентификатор должен быть положительным.")
        }
        if (health <= MIN_HEALTH) {
            throw InvalidDataException("Здоровье должно быть положительным.")
        }
        if (heartCount !in (MIN_HEART_COUNT..MAX_HEART_COUNT)) {
            throw InvalidDataException(
                "Количество сердец должно входить в диапазон $MIN_HEART_COUNT..$MAX_HEART_COUNT"
            )
        }
    }

    override fun compareTo(other: SpaceMarine): Int {
        return compareValuesBy(
            this,
            other,
            SpaceMarine::name,
            SpaceMarine::creatorName,
            SpaceMarine::coordinates,
            SpaceMarine::health,
            SpaceMarine::heartCount,
            SpaceMarine::loyal,
            SpaceMarine::meleeWeapon,
            SpaceMarine::chapter
        )
    }

    override fun hashCode(): Int {
        return ((((((((id.hashCode() * 31 + name.hashCode()) * 31 + coordinates.hashCode()
                ) * 31 + creationDate.hashCode()
                ) * 31 + health.hashCode()
                ) * 31 + heartCount.hashCode()
                ) * 31 + loyal.hashCode()
                ) * 31 + (meleeWeapon?.hashCode() ?: 0)
                ) * 31 + (chapter?.hashCode() ?: 0) * 31 + creatorName.hashCode())
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SpaceMarine

        if (id != other.id) return false
        if (creatorName != other.creatorName)
            if (name != other.name) return false
        if (coordinates != other.coordinates) return false
        if (creationDate != other.creationDate) return false
        if (health != other.health) return false
        if (heartCount != other.heartCount) return false
        if (loyal != other.loyal) return false
        if (meleeWeapon != other.meleeWeapon) return false
        if (chapter != other.chapter) return false

        return true
    }

    override fun toString(): String {
        return """
        |Космический десантник $name #$id:
        |    Создатель: ${creatorName ?: "сервер"}
        |    Координаты: $coordinates
        |    Дата создания: ${creationDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}
        |    Здоровье: $health
        |    Количество сердец: $heartCount
        |    Лояльный: ${if (loyal) "да" else "нет"}
        |    Оружие ближнего боя: ${meleeWeapon ?: "отсутствует"}
        |    Глава: ${chapter?.let { "\n" + it.toString().prependIndent("\t\t") } ?: "неизвестна"}
                """.trimMargin()
    }
}
