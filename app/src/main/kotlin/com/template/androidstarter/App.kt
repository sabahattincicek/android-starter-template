package com.template.androidstarter

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Application entry point. Hosts the Hilt dependency graph and sets up app-wide tools such as
 * logging.
 */
@HiltAndroidApp
class App : Application() {

    /** Plants the Timber debug tree so logs are printed in debug builds only. */
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Timber.d("Application created")
    }
}
