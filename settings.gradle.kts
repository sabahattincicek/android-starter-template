pluginManagement {
    // Convention plugins shared by all modules (see build-logic/README.md).
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AndroidStarter"

// Lets modules depend on each other as projects.core.common instead of project(":core:common").
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

// The folder tree follows the clean architecture layers; see the Architecture section of AGENTS.md.

// Application shell: App, MainActivity, navigation graph
include(":app")

// Shared building blocks with no business logic
include(":core:common")
include(":core:testing")

// Domain layer: models, repository interfaces, use cases (pure Kotlin)
include(":domain")

// Data layer: repository implementations, local (Room, DataStore) and remote (Retrofit) sources
include(":data")

// Presentation layer: shared UI, and one module per feature under presentation/features/
include(":presentation:ui")
// include(":presentation:features:<name>")
