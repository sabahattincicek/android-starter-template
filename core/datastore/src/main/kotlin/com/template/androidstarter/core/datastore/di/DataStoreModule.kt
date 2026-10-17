// OPTIONAL — part of :core:datastore. Delete the whole module if the project does not use DataStore
// (see "Removing an optional module" in SETUP.md).
package com.template.androidstarter.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import timber.log.Timber
import javax.inject.Singleton

/**
 * Hilt module providing the app's Preferences [DataStore]. There must be only one instance per
 * file, which is why it is a singleton.
 */
@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    private const val PREFERENCES_FILE = "app_preferences"

    /** Provides the Preferences DataStore backed by the `app_preferences` file. */
    @Provides
    @Singleton
    fun providePreferencesDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        Timber.d("Creating Preferences DataStore: file=%s", PREFERENCES_FILE)
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile(PREFERENCES_FILE) },
        )
    }
}
