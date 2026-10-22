# Android Starter

A multi-module Android starter template: Jetpack Compose, Material 3, Hilt, Navigation 3, Room,
DataStore, Retrofit, and Timber, organized with Clean Architecture and MVVM.

## First-Time Setup

If you are using this template for the first time, **follow the instructions in
[`docsagent/SETUP.md`](docsagent/SETUP.md) before starting development**.

The setup guide covers renaming the package and project with one Gradle task, removing the optional
parts you do not need, replacing the placeholders, and the AI assistant files: `AGENTS.md` in the
root, and the development log, roadmap, and setup guide in `docsagent/`. After the setup, the AI
interviews you about the app and its design
([`docsagent/PROJECT_DISCOVERY.md`](docsagent/PROJECT_DISCOVERY.md)) and writes the app plan and
the design criteria from your answers into `docsagent/`.

> **Do not skip the setup steps.**

## Architecture

Clean Architecture with MVVM. Each layer is a Gradle module, so the folder tree shows the layers:

```text
app/                       application shell: App, MainActivity, navigation graph
core/
  common/                  Resource, DispatcherProvider, utilities
  testing/                 unit test helpers
domain/                    models, repository interfaces, use cases (pure Kotlin)
data/                      repository implementations
  local/                   Room database, DataStore           (optional)
  remote/                  Retrofit APIs and DTOs             (optional)
presentation/
  ui/                      theme, shared composables, UiText
  features/<name>/         one module per screen or flow
build-logic/               convention plugins
docsagent/                 AI working documents: dev log, roadmap, setup, architecture, features, services, app plan, design
scripts/                   build, test, and lint with a short summary (for AI agents), token usage report
```

```text
presentation/features  ──▶  domain  ◀──  data
```

Features call use cases in `domain`; `data` implements the domain's repository interfaces. See
[`docsagent/ARCHITECTURE.md`](docsagent/ARCHITECTURE.md) for the full folder tree and how to add a
feature module, and the Architecture section of [`AGENTS.md`](AGENTS.md) for the rules between the
layers.

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

## Saving Tokens: Tips for the Developer

The AI rules already keep each session lean: short build output, reading only what a task needs,
and documents (`docsagent/FEATURES.md`, `docsagent/SERVICES.md`) that answer "where is this?"
without a code search. These habits on your side save more:

- **Start a new conversation after every task** (`/clear` in Claude Code). Every message resends
  the whole conversation, so a long session gets more expensive with each step. The AI reminds you
  at the end of each task; DEVLOG and ROADMAP carry the context to the next session.
- **Keep tasks small.** A ROADMAP task should fit in one session. Split anything bigger first.
- **Ask for a plan before large changes.** Fixing a wrong approach after the code is written costs
  far more than correcting a plan.
- **Pick the model for the job.** Routine work (a new screen, tests, small fixes) runs well on a
  smaller, cheaper model (in Claude Code: Sonnet). Keep the largest model for architecture
  decisions and hard bugs. Switch with `/model`.
- **Point to the place.** "Fix the retry button in `ProfileScreen.kt`" costs less than "the retry
  button is broken somewhere", because the AI does not have to search for it.
- **Paste only the relevant part of an error.** For a crash, the exception and the first lines of
  the stack trace are enough.

## Common Commands

```bash
scripts/check.sh               # build, test, and lint with a short summary (Windows: scripts\check.ps1)
python3 scripts/token_usage.py report  # token usage of past Claude Code sessions (Windows: python)
./gradlew assembleDebug        # build the debug APK
./gradlew testDebugUnitTest    # run all unit tests
./gradlew lint                 # run Android lint on all modules
```
