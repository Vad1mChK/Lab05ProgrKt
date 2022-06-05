package ru.vad1mchk.progr.lab05.server.database

enum class DatabaseQuery(val query: String) {
    CREATE_SPACE_MARINES_TABLE("""
        CREATE TABLE IF NOT EXISTS space_marines (
            id SERIAL PRIMARY KEY,
            creator_id INT REFERENCES smm_users(id) ON DELETE CASCADE,
            name VARCHAR(255) NOT NULL CHECK(name <> ''),
            coordinates_id INT NOT NULL REFERENCES coordinates(id) ON UPDATE NO ACTION ON DELETE RESTRICT,
            creation_date DATE NOT NULL DEFAULT NOW(),
            health DOUBLE PRECISION NOT NULL CHECK(health > 0.0),
            heart_count BIGINT NOT NULL CHECK(heart_count BETWEEN 1 AND 3),
            loyal BOOLEAN NOT NULL,
            melee_weapon_id INT REFERENCES melee_weapons(id) ON UPDATE NO ACTION ON DELETE SET NULL,
            chapter_id INT REFERENCES chapters(id) ON UPDATE NO ACTION ON DELETE SET NULL
        )
    """.trimIndent()),
    CREATE_COORDINATES_TABLE("""
        CREATE TABLE IF NOT EXISTS coordinates (
            id SERIAL PRIMARY KEY,
            x INT NOT NULL CHECK(x >= -817),
            y REAL NOT NULL,
            UNIQUE(x, y)
        )
    """.trimIndent()),
    CREATE_MELEE_WEAPON_TABLE("""
        CREATE TABLE IF NOT EXISTS melee_weapons (
            id SERIAL PRIMARY KEY,
            value VARCHAR(16) UNIQUE CHECK(value IN ('POWER_SWORD', 'CHAIN_SWORD', 'CHAIN_AXE'))
        )
    """.trimIndent()),
    CREATE_CHAPTER_TABLE("""
        CREATE TABLE IF NOT EXISTS chapters (
            id SERIAL PRIMARY KEY,
            name VARCHAR(255) NOT NULL CHECK(name <> ''),
            parent_legion VARCHAR(255) DEFAULT NULL,
            marines_count INT NOT NULL CHECK(marines_count BETWEEN 1 AND 1000),
            UNIQUE(name, parent_legion)
        )
    """.trimIndent()),
    CREATE_USER_TABLE("""
        CREATE TABLE IF NOT EXISTS smm_users (
            id SERIAL PRIMARY KEY,
            user_name VARCHAR(255) UNIQUE NOT NULL,
            password VARCHAR(255)
        )
    """.trimIndent()),
    INSERT_CONSTANT_INTO_MELEE_WEAPON("INSERT INTO melee_weapons(value) VALUES(?) ON CONFLICT DO NOTHING"),
    INSERT_SPACE_MARINE_WITH_NON_NULL_CHAPTER("""
        WITH coordinate AS (
            INSERT INTO coordinates(x, y) VALUES (?, ?) ON CONFLICT DO NOTHING RETURNING id
        ), chapter AS (
            INSERT INTO chapters(name, parent_legion, marines_count) VALUES (?, ?, ?) ON CONFLICT DO NOTHING
                RETURNING ID
        ) INSERT INTO space_marines
        (creator_id, name, coordinates_id, health, heart_count, loyal, melee_weapon_id, chapter_id)
        VALUES (
                   ?, ?,
                   (SELECT id FROM coordinate UNION ALL (SELECT id FROM coordinates WHERE x = ? AND y = ?)),
                   ?, ?, ?,
                   (SELECT melee_weapons.id FROM melee_weapons WHERE value = ?),
                   (SELECT id FROM chapter UNION (
                       SELECT id FROM chapters WHERE name = ? AND parent_legion = ? AND marines_count = ?)
                   )
        ) RETURNING id
    """.trimIndent()),
    INSERT_SPACE_MARINE_WITH_NULL_CHAPTER("""
        WITH coordinate AS (
            INSERT INTO coordinates(x, y) VALUES (?, ?) ON CONFLICT DO NOTHING RETURNING id
        ) INSERT INTO space_marines
        (creator_id, name, coordinates_id, health, heart_count, loyal, melee_weapon_id)
        VALUES (
            ?, ?,
            (SELECT id FROM coordinate UNION ALL (SELECT id FROM coordinates WHERE x = ? AND y = ?)),
            ?, ?, ?,
            (SELECT melee_weapons.id FROM melee_weapons WHERE value = ?)
        ) RETURNING id
    """.trimIndent()),
    UPDATE_SPACE_MARINE_WITH_NON_NULL_CHAPTER("""
        WITH coordinate AS (
            INSERT INTO coordinates(x, y) VALUES (?, ?) ON CONFLICT DO NOTHING RETURNING id
        ), chapter AS (
            INSERT INTO chapters(name, parent_legion, marines_count) VALUES (?, ?, ?) ON CONFLICT DO NOTHING 
            RETURNING ID
        ) UPDATE space_marines
        SET name = ?,
            coordinates_id = (SELECT id FROM coordinate UNION ALL (SELECT id FROM coordinates WHERE x = ? AND y = ?)),
            health = ?,
            heart_count = ?,
            loyal = ?,
            melee_weapon_id = (SELECT melee_weapon_id FROM melee_weapons WHERE value = ?),
            chapter_id = (SELECT id FROM chapter UNION ALL (
                SELECT id FROM chapters WHERE name = ? AND parent_legion = ? AND marines_count = ?
            ))
        WHERE id = ?
    """.trimIndent()),
    UPDATE_SPACE_MARINE_WITH_NULL_CHAPTER("""
        WITH coordinate AS (
            INSERT INTO coordinates(x, y) VALUES (?, ?) ON CONFLICT DO NOTHING RETURNING id
        ) UPDATE space_marines
        SET name = ?,
            coordinates_id = (SELECT id FROM coordinate UNION ALL (SELECT id FROM coordinates WHERE x = ? AND y = ?)),
            health = ?,
            heart_count = ?,
            loyal = ?,
            melee_weapon_id = (SELECT melee_weapon_id FROM melee_weapons WHERE value = ?),
            chapter_id = null
        WHERE id = ?
    """.trimIndent()),
    INSERT_USER("INSERT INTO smm_users(user_name, password) VALUES (?, ?) RETURNING id"),
    SELECT_USER("SELECT id FROM smm_users WHERE user_name = ? AND password = ?"),
    SELECT_ALL_SPACE_MARINES("""
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
                JOIN smm_users creator on creator.id = space_marines.creator_id
                LEFT OUTER JOIN coordinates coordinate on coordinate.id = space_marines.coordinates_id
                LEFT OUTER JOIN melee_weapons melee_weapon on melee_weapon.id = space_marines.melee_weapon_id
                LEFT OUTER JOIN chapters chapter on chapter.id = space_marines.chapter_id
    """.trimIndent()),
    SELECT_ALL_USER_NAMES("SELECT user_name FROM smm_users"),
    DELETE_SPACE_MARINES_BY_AUTHOR("""
        DELETE FROM space_marines WHERE creator_id = (SELECT id FROM smm_users where user_name = ? )
    """.trimIndent()),
    DELETE_SPACE_MARINE_BY_ID("DELETE FROM space_marines WHERE id = ?")
}