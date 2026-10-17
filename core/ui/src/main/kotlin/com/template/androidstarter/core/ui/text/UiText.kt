package com.template.androidstarter.core.ui.text

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

/**
 * User-facing text that a ViewModel can hold without a [Context]: either a string resource with
 * format arguments or an already resolved string. The UI resolves it when it draws.
 *
 * - From a resource: `UiText.StringResource(R.string.hello_user, userName)`
 * - From a dynamic value, e.g. a server message: `UiText.DynamicString(message)`
 *
 * Resolve it with [asString] in a composable, or `asString(context)` elsewhere.
 */
sealed interface UiText {
    data class DynamicString(val value: String) : UiText

    class StringResource(
        @StringRes val resId: Int,
        vararg val args: Any,
    ) : UiText {
        /** Two resources are equal when they point to the same string with the same arguments. */
        override fun equals(other: Any?): Boolean =
            other is StringResource && resId == other.resId && args.contentEquals(other.args)

        /** Hash code consistent with [equals]. */
        override fun hashCode(): Int = 31 * resId + args.contentHashCode()
    }

    /** Resolves this text to a plain string using [context], for use outside of Compose. */
    fun asString(context: Context): String = when (this) {
        is DynamicString -> value
        is StringResource -> context.getString(resId, *args)
    }

    /** Resolves this text to a plain string inside a composable. */
    @Composable
    fun asString(): String = when (this) {
        is DynamicString -> value
        is StringResource -> stringResource(resId, *args)
    }
}
