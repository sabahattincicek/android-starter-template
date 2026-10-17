package com.template.androidstarter.feature.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber
import javax.inject.Inject

/**
 * Holds the state of the home screen and handles its [HomeEvent]s. Real features inject use cases
 * from `:core:domain` here; this example only keeps a counter.
 */
@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())

    /** The current state of the home screen. */
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    /** Applies the user action [event] to the UI state. */
    fun onEvent(event: HomeEvent) {
        Timber.d("onEvent: %s", event)
        when (event) {
            HomeEvent.CounterTapped -> _uiState.update { it.copy(tapCount = it.tapCount + 1) }
            HomeEvent.CounterReset -> _uiState.update { it.copy(tapCount = 0) }
        }
    }
}
