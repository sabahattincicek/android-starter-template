package com.template.androidstarter.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.template.androidstarter.core.ui.R
import com.template.androidstarter.core.ui.theme.AppTheme

/**
 * Full-screen error state shown when a screen fails to load: the [message] and, when [onRetry] is
 * given, a retry button.
 */
@Composable
fun ErrorView(
    message: String,
    modifier: Modifier = Modifier,
    onRetry: (() -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
        )
        if (onRetry != null) {
            Button(onClick = onRetry) {
                Text(text = stringResource(R.string.core_ui_retry))
            }
        }
    }
}

/** Android Studio preview of [ErrorView] with a retry button. */
@Preview(showBackground = true)
@Composable
private fun ErrorViewPreview() {
    AppTheme {
        ErrorView(message = stringResource(R.string.core_ui_error_generic), onRetry = {})
    }
}
