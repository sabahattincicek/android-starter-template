// Shared building blocks with no UI and no business logic.
//
//   result/       Resource, the outcome of a data operation
//   dispatcher/   DispatcherProvider, injectable coroutine dispatchers
//   util/         small Kotlin extensions and helpers
//   di/           DispatcherModule
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
