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
- **Modules:** `:app`, `:feature:home`, and `:core:{common, model, domain, data, ui, network,
  database, datastore, testing}`. Dependency direction and rules are in `AGENTS.md`.
- **App:** `App` (`@HiltAndroidApp`, plants the Timber debug tree in debug builds), `MainActivity`
  (`@AndroidEntryPoint`, edge-to-edge), `AppNavDisplay` (Navigation 3 `NavDisplay` with saveable
  state and per-entry ViewModel store decorators). Release builds use R8 (minify + shrink resources).
- **Example feature `:feature:home`:** tap counter showing the feature layout: `HomeKey`
  (`@Serializable NavKey`), `homeEntry()`, `HomeRoute` (stateful), `HomeScreen` (stateless, with
  preview), `HomeViewModel` (`StateFlow<HomeUiState>`, `onEvent(HomeEvent)`), and
  `HomeViewModelTest`.
- **Core utilities:** `Resource` (Success / Error / Loading) with `Flow.asResource()`,
  `DispatcherProvider` provided by `DispatcherModule`, `UiText`, `AppTheme`, `ErrorView`,
  `LoadingView`.
- **Optional modules, ready to use:** `:core:network` (`NetworkModule`: Json, OkHttp with debug-only
  body logging, Retrofit with `BuildConfig.BASE_URL` = `https://example.com/`), `:core:database`
  (`AppDatabase` with the placeholder `ExampleEntity`/`ExampleDao`, schemas exported to
  `core/database/schemas`), `:core:datastore` (Preferences `DataStore` singleton).
- **Empty, ready to fill:** `:core:model`, `:core:domain` (`repository/`, `usecase/`),
  `:core:data` (`repository/`, empty `RepositoryModule`).
- **Tests:** JUnit 4, MockK, Turbine, and kotlinx-coroutines-test in every module; `:core:testing`
  provides `MainDispatcherRule` and `TestDispatcherProvider`. `ResourceTest` and
  `HomeViewModelTest` included.
- **CI:** `.github/workflows/ci.yml` runs `assembleDebug`, `testDebugUnitTest`, and `lint` on every
  push and pull request.
