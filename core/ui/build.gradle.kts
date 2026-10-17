// Shared UI: the app theme (theme/), composables used by several screens (component/), and UiText
// (text/) for passing user-facing text from ViewModels without a Context.
plugins {
    alias(libs.plugins.starter.android.library)
    alias(libs.plugins.starter.android.compose)
}

android {
    namespace = "com.template.androidstarter.core.ui"
}
