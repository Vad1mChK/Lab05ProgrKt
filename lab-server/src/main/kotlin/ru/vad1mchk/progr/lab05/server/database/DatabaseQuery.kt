package ru.vad1mchk.progr.lab05.server.database

enum class DatabaseQuery(val query: String) {
    CREATE_SPACE_MARINES_TABLE(
        """
        CREATE TABLE IF NOT EXISTS space_marines (
            id SERIAL PRIMARY KEY,
            creator_id INT DEFAULT NULL,
            name VARCHAR(256) UNIQUE NOT NULL CHECK (name <> ''),
            coordinates_id INT NOT NULL,
            FOREIGN KEY (coordinates_id) REFERENCES coordinates ON UPDATE NO ACTION ON DELETE RESTRICT,
            creation_date DATE NOT NULL DEFAULT NOW(),
            health DOUBLE PRECISION NOT NULL CHECK (health > 0.0),
            heart_count BIGINT NOT NULL CHECK (heart_count BETWEEN 1 AND 3),
            loyal BOOLEAN NOT NULL,
            melee_weapon_id INT REFERENCES melee_weapons ON UPDATE NO ACTION ON DELETE SET NULL,
            chapter_id INT REFERENCES chapters ON UPDATE NO ACTION ON DELETE SET NULL
        )
    """.trimIndent()
    ),
    CREATE_COORDINATES_TABLE(
        """
        CREATE TABLE IF NOT EXISTS coordinates (
            id SERIAL PRIMARY KEY,
            x INT NOT NULL CHECK(x >= -817),
            y REAL NOT NULL,
            UNIQUE(x, y)
        )
    """.trimIndent()
    ),
    CREATE_MELEE_WEAPON_TABLE(
        """
        CREATE TABLE IF NOT EXISTS melee_weapons (
            id SERIAL PRIMARY KEY,
            value VARCHAR(16) NULL UNIQUE
        )
    """.trimIndent()
    ),
    CREATE_CHAPTER_TABLE(
        """
        CREATE TABLE IF NOT EXISTS chapters (
            id SERIAL PRIMARY KEY,
            name VARCHAR(255) NOT NULL CHECK(name <> ''),
            parent_legion VARCHAR(255) DEFAULT NULL,
            marines_count INT NOT NULL CHECK(marines_count BETWEEN 1 AND 1000),
            UNIQUE(name, parent_legion)
        )
    """.trimIndent()
    ),
    CREATE_USER_TABLE(
        """
        CREATE TABLE IF NOT EXISTS smm_users (
            id SERIAL PRIMARY KEY,
            user_name VARCHAR(255) UNIQUE NOT NULL,
            password VARCHAR(255)
        )
    """.trimIndent()
    ),
    INSERT_CONSTANT_INTO_MELEE_WEAPON("INSERT INTO melee_weapons(value) VALUES(?) ON CONFLICT DO NOTHING"),
    INSERT_SPACE_MARINE_WITH_CHAPTER(
        """
        WITH coordinate as (INSERT INTO coordinates(x, y) VALUES (?, ?) ON CONFLICT DO NOTHING RETURNING id),
            chapter as (INSERT INTO chapters(name, parent_legion, marines_count) VALUES (
                ?, ?, ?
            ) ON CONFLICT DO NOTHING RETURNING id) INSERT INTO space_marines(
                creator_id, name, coordinates_id, creation_date, health, heart_count, loyal, melee_weapon_id, chapter_id
            ) VALUES (
                ?, ?,
            (SELECT id FROM coordinate UNION ALL(SELECT id FROM coordinates WHERE x = ? AND y = ?)),
                ?, ?, ?, ?,
            (SELECT id FROM melee_weapons WHERE value = ?),
            (SELECT id FROM chapter UNION ALL(
                SELECT id FROM chapters WHERE name = ? AND parent_legion = ? AND marines_count = ?)
            )
        ) RETURNING id
    """.trimIndent()
    ),
    INSERT_SPACE_MARINE_WITHOUT_CHAPTER(
        """
        WITH coordinate as (INSERT INTO coordinates(x, y) VALUES (?, ?) ON CONFLICT DO NOTHING RETURNING id)
        INSERT INTO space_marines(
            creator_id, name, coordinates_id, creation_date, health, heart_count, loyal, melee_weapon_id)
        VALUES (
            ?, ?,
            (SELECT id FROM coordinate UNION ALL(SELECT id FROM coordinates WHERE x = ? AND y = ?)),
            ?, ?, ?, ?,
            (SELECT id FROM melee_weapons WHERE value = ?)
        ) RETURNING id
    """.trimIndent()
    ),
    UPDATE_SPACE_MARINE_WITH_CHAPTER(
        """
        WITH coordinate as (INSERT INTO coordinates(x, y) VALUES (?, ?) ON CONFLICT DO NOTHING RETURNING id),
        chapter as (INSERT INTO chapters(name, parent_legion, marines_count) VALUES (
            ?, ?, ?
        ) ON CONFLICT DO NOTHING RETURNING id)
            UPDATE space_marines SET
            name = ?,
            coordinates_id = (SELECT id FROM coordinate UNION ALL (SELECT id FROM coordinates WHERE x = ? AND y = ?)),
            health = ?,
            heart_count = ?,
            loyal = ?,
            melee_weapon_id = (SELECT id FROM melee_weapons WHERE value = ?),
            chapter_id = (SELECT id FROM chapter UNION ALL
                (SELECT id FROM chapters WHERE name = ? AND parent_legion = ? AND marines_count = ?)
            ) WHERE space_marines.id = ?
    """.trimIndent()
    ),
    UPDATE_SPACE_MARINE_WITHOUT_CHAPTER(
        """
        WITH coordinate as (INSERT INTO coordinates(x, y) VALUES (?, ?) ON CONFLICT DO NOTHING RETURNING id)
        UPDATE space_marines SET
             name = ?,
             coordinates_id = (SELECT id FROM coordinate UNION ALL (SELECT id FROM coordinates WHERE x = ? AND y = ?)),
             health = ?,
             heart_count = ?,
             loyal = ?,
             melee_weapon_id = (SELECT id FROM melee_weapons WHERE value = ?),
             chapter_id = NULL WHERE space_marines.id = ?
    """.trimIndent()
    ),
    INSERT_USER("INSERT INTO smm_users(user_name, password) VALUES (?, ?) RETURNING id"),
    SELECT_USER("SELECT id FROM smm_users WHERE user_name = ? AND password = ?"),
    SELECT_ALL_SPACE_MARINES(
        """
        SELECT space_marines.id,
            creator.user_name,
            space_marines.name,
            coordinate.x,
            coordinate.y,
            space_marines.creation_date,
            space_marines.health,
            space_marines.heart_count,
            space_marines.loyal,
            melee_weapon.value,
            chapter.name,
            chapter.parent_legion,
            chapter.marines_count
        FROM space_marines
                LEFT OUTER JOIN smm_users creator on creator.id = space_marines.creator_id
                JOIN coordinates coordinate on coordinate.id = space_marines.coordinates_id
                LEFT OUTER JOIN melee_weapons melee_weapon on melee_weapon.id = space_marines.melee_weapon_id
                LEFT OUTER JOIN chapters chapter on chapter.id = space_marines.chapter_id
                ORDER BY space_marines.name
    """.trimIndent()
    ),
    SELECT_ALL_USER_NAMES("SELECT user_name FROM smm_users"),
    DELETE_SPACE_MARINES_BY_CREATOR(" DELETE FROM space_marines WHERE creator_id = ?"),
    DELETE_SPACE_MARINE_BY_ID("DELETE FROM space_marines WHERE id = ?"),
    DELETE_ALL_SPACE_MARINES(
        """
        DELETE FROM space_marines
    """.trimIndent()
    )
}