// Business models shared by the domain, data, and feature modules. Plain Kotlin only: no Android,
// Retrofit, Room, or serialization types (DTOs and entities stay in their own modules).
plugins {
    alias(libs.plugins.starter.android.library)
}

android {
    namespace = "com.template.androidstarter.core.model"
}
