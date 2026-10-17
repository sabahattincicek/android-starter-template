// The application module: App (Hilt root), MainActivity, and the navigation graph that ties the
// feature modules together. Keep it thin; screens live in :feature:*, shared code in :core:*.
plugins {
    alias(libs.plugins.starter.android.application)
    alias(libs.plugins.starter.android.compose)
    alias(libs.plugins.starter.hilt)
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
    implementation(projects.core.ui)
    // Puts the repository, network, database, and datastore Hilt modules into the app's graph.
    implementation(projects.core.data)

    implementation(projects.feature.home)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)

    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}
