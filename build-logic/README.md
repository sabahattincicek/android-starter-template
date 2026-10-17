# build-logic

Convention plugins shared by every module. A module applies one or more of them in its `plugins {}`
block instead of repeating the same Android, Compose, and Hilt setup in each `build.gradle.kts`.

| Plugin id | Applies | Used by |
|---|---|---|
| `starter.android.application` | Android application, SDK levels, Java version, Timber, unit test libraries | `:app` |
| `starter.android.library` | Android library, SDK levels, Java version, Timber, unit test libraries | every `:core:*` and `:feature:*` module |
| `starter.android.compose` | Compose compiler and the Compose BOM libraries | modules with composables |
| `starter.hilt` | KSP, Hilt plugin, Hilt runtime and compiler | modules with `@Inject`, `@Module`, `@HiltViewModel` |
| `starter.android.feature` | library + compose + hilt + serialization, and the core modules every screen needs | every `:feature:*` module |
| `starter.rename` | the `renameProject` task (see `SETUP.md`) | root project |

SDK levels (`compileSdk`, `targetSdk`, `minSdk`) are read from `gradle/libs.versions.toml`.
