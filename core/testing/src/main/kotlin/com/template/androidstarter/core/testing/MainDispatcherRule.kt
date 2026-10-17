package com.template.androidstarter.core.testing

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * JUnit rule that replaces [Dispatchers.Main] with [testDispatcher] for the duration of a test, so
 * code using `viewModelScope` can run in plain JVM unit tests.
 *
 * Usage: `@get:Rule val mainDispatcherRule = MainDispatcherRule()`
 */
@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {

    /** Installs [testDispatcher] as the main dispatcher before the test runs. */
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    /** Restores the real main dispatcher after the test. */
    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}
