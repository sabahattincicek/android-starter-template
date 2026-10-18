// OPTIONAL (Room) — delete this file if the project does not use a local database, together with
// local/database/ and the Room lines in data/build.gradle.kts (see "Removing an optional part" in docsagent/SETUP.md).
package com.template.androidstarter.data.di

import android.content.Context
import androidx.room.Room
import com.template.androidstarter.data.local.database.AppDatabase
import com.template.androidstarter.data.local.database.dao.ExampleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import timber.log.Timber
import javax.inject.Singleton

/**
 * Hilt module providing the [AppDatabase] and its DAOs. Add one `@Provides` function per DAO.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /** Provides the single [AppDatabase] instance stored in the app's private files. */
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        Timber.d("Creating AppDatabase: name=%s", AppDatabase.NAME)
        return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.NAME).build()
    }

    /** Provides the example DAO; remove it together with ExampleEntity. */
    @Provides
    fun provideExampleDao(database: AppDatabase): ExampleDao = database.exampleDao()
}
