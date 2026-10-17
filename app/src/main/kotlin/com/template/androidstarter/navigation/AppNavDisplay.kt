package com.template.androidstarter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay

/**
 * The app's navigation graph, built with Navigation 3. The back stack is a list of feature keys
 * (`NavKey`); pushing a key opens its screen and popping it goes back.
 *
 * To add a screen: define its key and `xxxEntry()` in the feature module, call it inside
 * `entryProvider` below, and push the key from a navigation callback. Once the first feature
 * exists, start the back stack with its key and delete [StartKey] and `StartPlaceholder.kt`.
 */
@Composable
fun AppNavDisplay(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(StartKey)

    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            // Keeps each screen's rememberSaveable state while it is on the back stack.
            rememberSaveableStateHolderNavEntryDecorator(),
            // Gives each screen its own ViewModels, cleared when the screen is popped.
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider = entryProvider {
            entry<StartKey> { StartPlaceholder() }
            // profileEntry()
        },
    )
}
