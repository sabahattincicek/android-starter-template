package com.template.androidstarter.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.template.androidstarter.core.ui.theme.AppTheme

/**
 * Stateful entry point of the home screen: gets the [HomeViewModel] from Hilt, collects its state,
 * and passes state and events to the stateless [HomeScreen].
 */
@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(uiState = uiState, onEvent = viewModel::onEvent, modifier = modifier)
}

/**
 * Stateless home screen: draws [uiState] and reports user actions through [onEvent].
 */
@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onEvent: (HomeEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.feature_home_title),
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(
                text = pluralStringResource(R.plurals.feature_home_tap_count, uiState.tapCount, uiState.tapCount),
                style = MaterialTheme.typography.bodyLarge,
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { onEvent(HomeEvent.CounterTapped) }) {
                    Text(text = stringResource(R.string.feature_home_tap))
                }
                OutlinedButton(onClick = { onEvent(HomeEvent.CounterReset) }, enabled = uiState.tapCount > 0) {
                    Text(text = stringResource(R.string.feature_home_reset))
                }
            }
        }
    }
}

/** Android Studio preview of [HomeScreen] after a few taps. */
@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    AppTheme {
        HomeScreen(uiState = HomeUiState(tapCount = 3), onEvent = {})
    }
}
