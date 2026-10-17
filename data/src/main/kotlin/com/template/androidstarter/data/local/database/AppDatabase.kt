// OPTIONAL (Room) — delete the whole local/database/ folder if the project does not use a local
// database, together with di/DatabaseModule.kt and the Room lines in data/build.gradle.kts.
package com.template.androidstarter.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.template.androidstarter.data.local.database.dao.ExampleDao
import com.template.androidstarter.data.local.database.entity.ExampleEntity

/**
 * The app's Room database. Register every entity in `entities` and expose one DAO per entity.
 * Bump [version] and add a migration whenever the schema changes.
 */
@Database(
    entities = [ExampleEntity::class],
    version = 1,
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {

    /** DAO of the example table; remove it together with [ExampleEntity]. */
    abstract fun exampleDao(): ExampleDao

    companion object {
        const val NAME = "app.db"
    }
}
