package com.template.androidstarter.core.common.di

import com.template.androidstarter.core.common.dispatcher.DefaultDispatcherProvider
import com.template.androidstarter.core.common.dispatcher.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import timber.log.Timber
import javax.inject.Singleton

/**
 * Hilt module providing the coroutine dispatchers used across the app.
 */
@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    /** Provides the production [DispatcherProvider]; unit tests pass a test provider instead. */
    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider {
        Timber.d("Providing DefaultDispatcherProvider")
        return DefaultDispatcherProvider()
    }
}
