# Android Starter

A multi-module Android starter template: Jetpack Compose, Material 3, Hilt, Navigation 3, Room,
DataStore, Retrofit, and Timber, organized with Clean Architecture and MVVM.

## First-Time Setup

If you are using this template for the first time, **follow the instructions in [`SETUP.md`](SETUP.md) before starting development**.

The setup guide covers renaming the package and project with one Gradle task, removing the optional
modules you do not need, replacing the example code, and the AI assistant files (`AGENTS.md`,
`DEVLOG.md`, `ROADMAP.md`).

> **Do not skip the setup steps.**

## Modules

```text
app/                 App, MainActivity, navigation graph
feature/home/        example screen (copy it for new features)
core/common/         Resource, DispatcherProvider
core/model/          domain models
core/domain/         repository interfaces, use cases
core/data/           repository implementations
core/ui/             theme, shared composables, UiText
core/network/        Retrofit + OkHttp            (optional)
core/database/       Room                         (optional)
core/datastore/      DataStore Preferences        (optional)
core/testing/        unit test helpers
build-logic/         convention plugins
```

See the Architecture section of [`AGENTS.md`](AGENTS.md) for the rules between modules.

## Stack

| Area | Library |
|---|---|
| Build | Gradle 9.8.1, AGP 9.4.1, Kotlin 2.4.21, KSP, version catalog, convention plugins |
| SDK | compileSdk / targetSdk 37, minSdk 26, Java 17 |
| UI | Jetpack Compose (BOM 2026.09.00), Material 3, Navigation 3 |
| DI | Hilt |
| Data | Room, DataStore Preferences, Retrofit + OkHttp, kotlinx.serialization |
| Async | Kotlin Coroutines and Flow |
| Logging | Timber |
| Tests | JUnit 4, MockK, Turbine, kotlinx-coroutines-test |
| CI | GitHub Actions: build, unit tests, and lint on every push and pull request |

## Common Commands

```bash
./gradlew assembleDebug        # build the debug APK
./gradlew testDebugUnitTest    # run all unit tests
./gradlew lint                 # run Android lint on all modules
```
