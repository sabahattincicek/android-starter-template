package com.template.androidstarter.feature.home

import app.cash.turbine.test
import com.template.androidstarter.core.testing.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Unit tests for [HomeViewModel].
 */
class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: HomeViewModel

    /** Creates a fresh ViewModel before each test. */
    @Before
    fun setUp() {
        viewModel = HomeViewModel()
    }

    /** The screen starts with a count of zero. */
    @Test
    fun initialState_hasZeroTaps() {
        assertEquals(HomeUiState(tapCount = 0), viewModel.uiState.value)
    }

    /** Each tap increases the count by one. */
    @Test
    fun counterTapped_incrementsCount() = runTest {
        viewModel.uiState.test {
            assertEquals(0, awaitItem().tapCount)
            viewModel.onEvent(HomeEvent.CounterTapped)
            assertEquals(1, awaitItem().tapCount)
            viewModel.onEvent(HomeEvent.CounterTapped)
            assertEquals(2, awaitItem().tapCount)
        }
    }

    /** Reset sets the count back to zero. */
    @Test
    fun counterReset_setsCountToZero() {
        viewModel.onEvent(HomeEvent.CounterTapped)
        viewModel.onEvent(HomeEvent.CounterReset)
        assertEquals(0, viewModel.uiState.value.tapCount)
    }
}
