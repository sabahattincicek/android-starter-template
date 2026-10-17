// Shared UI used by every feature module.
//
//   theme/        colors, typography, AppTheme
//   components/   composables used by several screens (ErrorView, LoadingView, ...)
//   text/         UiText, user-facing text passed from ViewModels without a Context
//   base/         base classes for ViewModels or screens, if the project wants them
plugins {
    alias(libs.plugins.starter.android.library)
    alias(libs.plugins.starter.android.compose)
}

android {
    namespace = "com.template.androidstarter.presentation.ui"
}
