// Test helpers shared by the unit tests of other modules. Added to a module with
// testImplementation(projects.core.testing); feature modules get it automatically.
plugins {
    alias(libs.plugins.starter.android.library)
}

android {
    namespace = "com.template.androidstarter.core.testing"
}

dependencies {
    api(projects.core.common)
    api(libs.junit)
    api(libs.kotlinx.coroutines.test)
    api(libs.turbine)
    api(libs.mockk)
}
