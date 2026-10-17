package com.template.androidstarter.feature.home.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.template.androidstarter.feature.home.HomeRoute
import kotlinx.serialization.Serializable

/**
 * Navigation 3 key of the home screen. Pushing it onto the back stack opens the screen; it is
 * serializable so the back stack survives process death.
 */
@Serializable
data object HomeKey : NavKey

/**
 * Registers the home screen in the app's navigation graph. Called from the `entryProvider` block in
 * `:app`.
 */
fun EntryProviderScope<NavKey>.homeEntry() {
    entry<HomeKey> {
        HomeRoute()
    }
}
