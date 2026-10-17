// OPTIONAL MODULE — delete :core:network if the project does not call an HTTP API.
// See "Removing an optional module" in SETUP.md.
//
// HTTP client setup: OkHttp, Retrofit, and kotlinx.serialization. API interfaces (api/) and DTOs
// (model/) of the project go here; repositories in :core:data map the DTOs to domain models.
plugins {
    alias(libs.plugins.starter.android.library)
    alias(libs.plugins.starter.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.template.androidstarter.core.network"

    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
        // Base URL of the backend. Replace it, or move it to build types / flavors per environment.
        buildConfigField("String", "BASE_URL", "\"https://example.com/\"")
    }
}

dependencies {
    api(libs.retrofit)
    api(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.kotlinx.serialization)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
}
