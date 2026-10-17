// Shared building blocks with no UI: Resource, DispatcherProvider, and small Kotlin helpers.
plugins {
    alias(libs.plugins.starter.android.library)
    alias(libs.plugins.starter.hilt)
}

android {
    namespace = "com.template.androidstarter.core.common"
}

dependencies {
    api(libs.kotlinx.coroutines.android)
}
