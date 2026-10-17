package com.template.androidstarter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.template.androidstarter.navigation.AppNavDisplay
import com.template.androidstarter.presentation.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * The single activity of the app. Hosts the Compose UI and is the Hilt entry point for everything
 * shown on screen.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /** Enables edge-to-edge drawing and shows the navigation graph inside the app theme. */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate: savedInstanceState=%s", if (savedInstanceState == null) "null" else "restored")
        enableEdgeToEdge()
        setContent {
            AppTheme {
                AppNavDisplay()
            }
        }
    }
}
