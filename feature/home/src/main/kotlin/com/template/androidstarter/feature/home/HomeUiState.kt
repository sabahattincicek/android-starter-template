package com.template.androidstarter.feature.home

/**
 * Everything the home screen draws. The ViewModel exposes one immutable instance at a time.
 */
data class HomeUiState(
    val tapCount: Int = 0,
)

/**
 * User actions on the home screen, sent to [HomeViewModel.onEvent].
 */
sealed interface HomeEvent {
    data object CounterTapped : HomeEvent
    data object CounterReset : HomeEvent
}
