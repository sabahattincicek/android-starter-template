// Data layer. Implements the repository interfaces of :domain and maps data types to domain models.
//
//   local/database/   Room: AppDatabase, entity/, dao/                       (optional)
//   local/datastore/  DataStore Preferences data sources                    (optional)
//   remote/api/       Retrofit API interfaces                               (optional)
//   remote/dto/       network request and response models (DTOs)            (optional)
//   repository/       repository implementations
//   di/               Hilt modules: RepositoryModule, NetworkModule, DatabaseModule, DataStoreModule
//
// Network, Room, and DataStore are optional: the lines that belong to each are marked below. See
// "Removing an optional part" in SETUP.md.
plugins {
    alias(libs.plugins.starter.android.library)
    alias(libs.plugins.starter.hilt)
    alias(libs.plugins.kotlin.serialization) // Network: DTOs are @Serializable
    alias(libs.plugins.room) // Room
}

android {
    namespace = "com.template.androidstarter.data"

    // Network: base URL of the backend. Replace it, or move it to build types / flavors per
    // environment.
    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
        buildConfigField("String", "BASE_URL", "\"https://example.com/\"")
    }
}

// Room: exported schemas are committed, so migrations can be written and tested against them.
room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    implementation(projects.domain)

    // Network
    implementation(libs.retrofit)
    implementation(libs.retrofit.kotlinx.serialization)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.kotlinx.serialization.json)

    // Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    // DataStore
    implementation(libs.androidx.datastore.preferences)

    testImplementation(projects.core.testing)
}
