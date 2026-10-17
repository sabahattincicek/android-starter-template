// Application shell: App (Hilt root), MainActivity, the navigation graph that ties the feature
// modules together (navigation/), and app-wide Hilt modules (di/). Keep it thin; screens live in
// :presentation:features:*, everything else in its own layer module.
plugins {
    alias(libs.plugins.starter.android.application)
    alias(libs.plugins.starter.android.compose)
    alias(libs.plugins.starter.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.template.androidstarter"

    defaultConfig {
        applicationId = "com.template.androidstarter"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.presentation.ui)
    // Puts the repository, network, database, and datastore Hilt modules into the app's graph.
    implementation(projects.data)

    // Feature modules: add one line per feature, e.g.
    // implementation(projects.presentation.features.profile)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)

    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}
