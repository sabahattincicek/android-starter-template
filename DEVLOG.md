# Development Log

A running record of what has been done in this project, so a new session (human or AI) can catch up
without reading the whole codebase.

- **Newest entries go at the top**, directly under this header. Read downward to go back in time.
- Entry heading format: `## YYYY-MM-DD HH:mm — Short title`
- Say what changed, why, and which files or areas were touched.
- Do not rewrite or delete older entries.

---

## 2026-10-18 01:20 — Project created from AndroidStarter template

Starting point of the project. What the template provides:

- **Build:** Gradle 9.8.1, AGP 9.4.1 with built-in Kotlin 2.4.21, KSP, version catalog in
  `gradle/libs.versions.toml`, type-safe project accessors (`projects.core.common`),
  configuration cache on. compileSdk/targetSdk 37, minSdk 26, Java 17.
- **Convention plugins** in `build-logic/`: `starter.android.application`,
  `starter.android.library`, `starter.android.compose`, `starter.hilt`, `starter.android.feature`,
  and `starter.rename` (the one-time `renameProject` task). SDK levels are read from the catalog.
- **Layout:** Clean Architecture, one Gradle module per layer, so the folder tree shows the
  architecture: `:app`, `:core:common`, `:core:testing`, `:domain`, `:data`, `:presentation:ui`, and
  feature modules under `presentation/features/` (none yet). Folder tree and rules are in the
  Architecture section of `AGENTS.md`. Intended but empty folders contain a `.gitkeep`.
- **App:** `App` (`@HiltAndroidApp`, plants the Timber debug tree in debug builds), `MainActivity`
  (`@AndroidEntryPoint`, edge-to-edge), `AppNavDisplay` (Navigation 3 `NavDisplay` with saveable
  state and per-entry ViewModel store decorators) starting at `StartKey`, a placeholder screen
  (`StartPlaceholder.kt`) that shows the app name until the first feature exists. Release builds use
  R8 (minify + shrink resources).
- **Core:** `Resource` (Success / Error / Loading) with `Flow.asResource()`, `DispatcherProvider`
  provided by `DispatcherModule`; `:core:testing` with `MainDispatcherRule` and
  `TestDispatcherProvider`.
- **Domain:** empty `model/`, `repository/`, `usecase/`.
- **Data:** empty `repository/`, `remote/api/`, `remote/dto/`, `local/datastore/`, and an empty
  `RepositoryModule`. Optional parts, ready to use: Network (`NetworkModule`: Json, OkHttp with
  debug-only body logging, Retrofit with `BuildConfig.BASE_URL` = `https://example.com/`), Room
  (`local/database/AppDatabase` with the placeholder `ExampleEntity`/`ExampleDao`, schemas
  exported to `data/schemas`), DataStore (Preferences `DataStore` singleton).
- **Presentation UI:** `AppTheme`, `ErrorView`, `LoadingView`, `UiText`, empty `base/`.
- **Tests:** JUnit 4, MockK, Turbine, and kotlinx-coroutines-test in every module; `ResourceTest`
  included.
- **CI:** `.github/workflows/ci.yml` runs `assembleDebug`, `testDebugUnitTest`, and `lint` on every
  push and pull request.
