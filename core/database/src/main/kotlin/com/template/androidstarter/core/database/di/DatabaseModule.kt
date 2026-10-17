// OPTIONAL — part of :core:database. Delete the whole module if the project does not use Room
// (see "Removing an optional module" in SETUP.md).
package com.template.androidstarter.core.database.di

import android.content.Context
import androidx.room.Room
import com.template.androidstarter.core.database.AppDatabase
import com.template.androidstarter.core.database.dao.ExampleDao
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
