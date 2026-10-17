// OPTIONAL — part of :core:database. Delete the whole module if the project does not use Room
// (see "Removing an optional module" in SETUP.md).
package com.template.androidstarter.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.template.androidstarter.core.database.dao.ExampleDao
import com.template.androidstarter.core.database.entity.ExampleEntity

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
