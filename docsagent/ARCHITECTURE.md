# Architecture

The folder layout, the parts of a feature module, and how to add one. The rules between the layers
are in the Architecture section of `AGENTS.md`.

This project follows **Clean Architecture** with **MVVM** and unidirectional data flow. Each layer
is a Gradle module, and the folder tree shows the layers. Respect the structure:

```text
app/                          :app — application shell
  navigation/                 AppNavDisplay (the Navigation 3 graph)
  di/                         app-wide Hilt modules
core/                         shared building blocks, no business logic
  common/                     :core:common
    result/                   Resource
    dispatcher/               DispatcherProvider
    util/                     extensions and small helpers
    di/                       DispatcherModule
  testing/                    :core:testing — unit test helpers
domain/                       :domain — business rules, pure Kotlin
  model/                      business models
  repository/                 repository interfaces
  usecase/                    use cases
data/                         :data — implements the domain
  local/database/             Room: AppDatabase, entity/, dao/        (optional)
  local/datastore/            DataStore data sources                  (optional)
  remote/api/                 Retrofit API interfaces                 (optional)
  remote/dto/                 network models (DTOs)                   (optional)
  repository/                 repository implementations
  di/                         RepositoryModule, NetworkModule, DatabaseModule, DataStoreModule
presentation/                 UI layer
  ui/                         :presentation:ui — shared UI
    theme/                    colors, typography, AppTheme
    components/               shared composables (ErrorView, LoadingView)
    text/                     UiText
    base/                     base classes for ViewModels or screens, if wanted
  features/<name>/            :presentation:features:<name> — one module per screen or flow
build-logic/                  convention plugins (see build-logic/README.md)
docsagent/                    AI working documents: DEVLOG, ROADMAP (and its archive), SETUP,
                              first session, architecture, features, services, project discovery,
                              app plan, design
scripts/                      check.ps1 and check.sh: build, test, and lint with a short summary
```

A feature module contains `<Name>Screen` (stateless), `<Name>Route` (stateful, gets the ViewModel),
`<Name>ViewModel`, `<Name>UiState` and `<Name>Event`, and `navigation/` with its `NavKey` and
`xxxEntry()` function.

## Adding a feature module

1. Create `presentation/features/<name>/build.gradle.kts`:

   ```kotlin
   // <What the feature does, in one line.>
   plugins {
       alias(libs.plugins.starter.android.feature)
   }

   android {
       namespace = "<base package>.presentation.features.<name>"
   }
   ```

2. Put the code in `src/main/kotlin/<base package path>/presentation/features/<name>/`, its strings
   in `src/main/res/values/strings.xml`, and its tests in `src/test/kotlin/...`.
3. Add `include(":presentation:features:<name>")` to `settings.gradle.kts`.
4. Add `implementation(projects.presentation.features.<name>)` to `app/build.gradle.kts` and call
   its `xxxEntry()` in `AppNavDisplay`. For the first feature, start the back stack with its key
   and delete `StartKey` and `app/.../navigation/StartPlaceholder.kt`.
5. Add a row for the feature to [`FEATURES.md`](FEATURES.md).
