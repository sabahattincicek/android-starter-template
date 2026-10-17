plugins {
    `kotlin-dsl`
}

group = "starter.buildlogic"

dependencies {
    // Only compiled against; the root build puts the real plugins on the classpath.
    compileOnly(libs.android.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = libs.plugins.starter.android.application.get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = libs.plugins.starter.android.library.get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidCompose") {
            id = libs.plugins.starter.android.compose.get().pluginId
            implementationClass = "AndroidComposeConventionPlugin"
        }
        register("androidFeature") {
            id = libs.plugins.starter.android.feature.get().pluginId
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("hilt") {
            id = libs.plugins.starter.hilt.get().pluginId
            implementationClass = "HiltConventionPlugin"
        }
        register("rename") {
            id = libs.plugins.starter.rename.get().pluginId
            implementationClass = "RenameProjectConventionPlugin"
        }
    }
}
