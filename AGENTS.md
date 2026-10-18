# AI Agent Rules

Rules for any AI coding assistant working in this repository. They are tool-agnostic, and
developers are free to edit them to fit the project.

Read this file fully at the start of every session, then follow the session start steps below.

---

## Project Settings

Filled in once, on the first session (see "First session" below).

- **AI tool:** `NOT SET` <!-- e.g. "Claude Code", "Codex", "Gemini CLI", "Cursor" -->
- **Git commits and pushes are made by:** `NOT SET` <!-- "AI" or "Developer" -->
- **Language for code, comments, logs, and commit messages:** `English`
- **Project discovery (app plan and design):** `NOT DONE` <!-- "DONE" once the plan and design files in docsagent/ are approved -->

The AI working documents (development log, roadmap, setup guide, project discovery, app plan,
design) live together in [`docsagent/`](docsagent/). Only this file and `README.md` stay in the
root, because tools load this file from there.

---

## 1. Session Start

At the start of every session, before writing any code:

1. Read [`docsagent/DEVLOG.md`](docsagent/DEVLOG.md) to learn what has been done so far. Do not
   re-read the whole codebase to rebuild that context; open only the files the current task needs.
2. Read [`docsagent/ROADMAP.md`](docsagent/ROADMAP.md) to see what is planned and what is still
   open.
3. Check the Project Settings above.
4. If [`docsagent/APP_PLAN.md`](docsagent/APP_PLAN.md) and
   [`docsagent/DESIGN.md`](docsagent/DESIGN.md) exist, read the parts that matter for the current
   task: the plan for scope and features, the design for any UI work.

### First session

If a value in Project Settings is still `NOT SET`, ask the developer and write the answer into
Project Settings. Do not ask again once it is set. Project discovery (question 3) has its own
rule below.

**1. Which AI tool is being used?**

> Which AI coding tool are you using for this project?

Then rename this file to the name that tool loads automatically, so the rules apply in every
session without the developer pointing to them:

| Tool | File name |
|---|---|
| Claude Code | `CLAUDE.md` |
| Gemini CLI | `GEMINI.md` |
| GitHub Copilot | `.github/copilot-instructions.md` |
| Codex, Cursor, and other tools that read `AGENTS.md` | keep `AGENTS.md` |
| Any other tool | the instruction file name that tool documents |

Keep the renamed file in the root (or in `.github/` for Copilot); tools do not look for it in
`docsagent/`. After renaming, update every reference to `AGENTS.md` in `README.md` and in the files
in `docsagent/`.

**2. Who makes git commits and pushes?**

> Should I make the git commits and pushes myself, or will you do them?

Record `AI` or `Developer`.

**3. Project discovery: the app plan and the design criteria**

Once the setup in [`docsagent/SETUP.md`](docsagent/SETUP.md) is finished, and while "Project
discovery" is `NOT DONE`, run the interview in
[`docsagent/PROJECT_DISCOVERY.md`](docsagent/PROJECT_DISCOVERY.md):

> The setup is done. Before we build anything, I would like to plan the app with you: first what it
> does and how it will grow, then how it should look. Shall we start?

It produces `docsagent/APP_PLAN.md`, `docsagent/DESIGN.md`, and `docsagent/design.html`. Follow the
rules in that file, and set "Project discovery" to `DONE` when the developer has approved all three. If the
developer wants to postpone it, offer it again at the next session start, and before building the
first feature.

---

## 2. Working Rules

- **Stay in scope.** Change only what the task needs. Do not refactor, reformat, or "improve"
  unrelated code along the way; mention it to the developer instead.
- **Ask before large or destructive changes:** deleting files or modules, rewriting a module,
  changing the architecture, or anything hard to undo.
- **Ask before adding a library.** Do not add, replace, or upgrade a dependency without the
  developer's approval. Say what it is for and what the alternatives are.
- **Follow the plan and the design.** Build what `docsagent/APP_PLAN.md` describes and style the UI
  with the tokens and components in `docsagent/DESIGN.md`; no colors, sizes, or fonts outside the
  theme. When a task changes the plan or the design, update those files (and
  `docsagent/design.html`) and their decision log in the same piece of work.
- **Language.** Write code identifiers, comments, log messages, and commit messages in the language
  set in Project Settings. User-facing text follows the app's own localization.

### Definition of done

A task is finished only when all of these are true:

1. The project builds (`./gradlew assembleDebug`).
2. Unit tests are updated and pass (`./gradlew testDebugUnitTest`).
3. Lint reports no new issues (`./gradlew lint`).
4. New and changed code follows the comment and logging rules below.
5. `docsagent/DEVLOG.md` has a new entry for the work.
6. The matching `docsagent/ROADMAP.md` tasks are marked `[x]`.

The same build, test, and lint commands run in CI on every push and pull request
(`.github/workflows/ci.yml`). Never report a task as done while any of these is missing; say what
is still open.

---

## 3. Documentation Comments

- **Every file or class** starts with a KDoc comment explaining what it is for and where it fits in
  the app. Every module's `build.gradle.kts` starts with a comment saying what the module holds.
- **Every function** has a comment of one or two sentences above it saying what it does.
- Keep comments in sync with the code. When behavior changes, update the comment in the same change.
- Explain purpose and intent, not the syntax.

```kotlin
/**
 * Loads the signed-in user's profile and exposes it to the profile screen as UI state.
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfile: GetProfileUseCase
) : ViewModel() {

    /** Fetches the profile again and updates the UI state with the result. */
    fun refresh() { ... }
}
```

---

## 4. Logging

- Every class that has behavior logs, using **Timber**:
  `Timber.d("Profile loaded: id=%s", profile.id)`. Classes with nothing to log are exempt: plain
  data holders (models, DTOs, entities, UI state, events, wrappers such as `Resource` and
  `UiText`), constants, theme definitions, and empty placeholders. Do not call `android.util.Log`
  directly. Timber tags each line with the calling class automatically, and it is silent in unit
  tests and in release builds (the debug tree is planted in `App` for debug builds only). Every
  module already has Timber through the convention plugins.
- Place logs wherever they help debugging: function entry for significant operations, state
  changes, branch decisions, network and database calls with their results, and every caught
  exception.
- Use the right level: `Timber.d` for flow and state, `Timber.i` for notable events, `Timber.w` for
  recoverable problems, `Timber.e` for failures (always pass the exception first:
  `Timber.e(exception, "Profile load failed")`).
- Log messages state what happened and include the relevant values, so a log line is useful on its
  own.
- Never log passwords, tokens, API keys, or personal user data.

---

## 5. Unit Tests

- Keep unit tests up to date at all times. Any change to behavior comes with the matching test
  change in the same piece of work.
- New use cases, repositories, and ViewModels get unit tests when they are written, in the
  `src/test` folder of the same module.
- Available in every module: JUnit 4, MockK, kotlinx-coroutines-test, and Turbine (for testing
  flows). `:core:testing` adds `MainDispatcherRule` (for ViewModels) and `TestDispatcherProvider`;
  feature modules get it automatically, `:domain` and `:data` already have it, other modules add
  `testImplementation(projects.core.testing)`.
- Inject `DispatcherProvider` instead of using `Dispatchers` directly, so tests can supply test
  dispatchers.
- Run the tests before calling a task done. Never leave failing tests behind, and never delete or
  weaken a test just to make it pass.

---

## 6. Development Log — `docsagent/DEVLOG.md`

[`docsagent/DEVLOG.md`](docsagent/DEVLOG.md) is the project's memory. It exists so that a new
session can understand the project without reading all the code.

- After finishing a piece of work, add an entry describing what was done.
- Every entry is stamped with date and time: `## YYYY-MM-DD HH:mm — Short title`.
- **New entries go at the top of the file**, directly under the header. The file reads from newest
  to oldest.
- Write what changed, why, and which files or areas were touched. Note decisions and anything a
  future session needs to know.
- Never rewrite or delete older entries.

---

## 7. Roadmap — `docsagent/ROADMAP.md`

[`docsagent/ROADMAP.md`](docsagent/ROADMAP.md) holds everything still to be done.

- Work is organized in **phases**. Each phase is broken into small tasks.
- Every task starts with a checkbox: `- [ ]` for open, `- [x]` for done.
- Mark a task `[x]` as soon as it is finished. Do not remove finished tasks.
- When new work comes up, add it to the right phase, or add a new phase.
- Before starting work, confirm the task exists in the roadmap; add it if it does not.

---

## 8. Architecture

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
docsagent/                    AI working documents: DEVLOG, ROADMAP, SETUP, project discovery,
                              app plan, design
```

A feature module contains `<Name>Screen` (stateless), `<Name>Route` (stateful, gets the ViewModel),
`<Name>ViewModel`, `<Name>UiState` and `<Name>Event`, and `navigation/` with its `NavKey` and
`xxxEntry()` function.

Rules:

- **Dependency direction:** `:app` → `:presentation:features:*` → `:domain` ← `:data`. Feature
  modules depend on `:domain`, `:presentation:ui`, and `:core:common`; never on `:data` or on each
  other, so they reach data only through use cases. `:domain` depends only on `:core:common`.
  Only `:app` depends on `:data`, to put its Hilt modules into the graph.
- **Domain stays pure Kotlin:** no Android framework, Retrofit, or Room types in `:domain` (Timber
  logging is the only exception).
- **Repositories:** interface in `domain/repository/`, implementation in `data/repository/`, bound
  in `data/di/RepositoryModule`.
- **Models:** DTOs stay in `data/remote/dto/` and entities in `data/local/database/entity/`;
  repositories map them to the domain models in `domain/model/`. They never reach the UI.
- **Use cases:** one use case does one thing and exposes it through `operator fun invoke`.
- **ViewModels:** expose a single immutable UI state as `StateFlow`, receive user actions as
  events through one `onEvent` function, and call use cases, not repositories.
- **Composables:** stateless where possible; state is hoisted to the ViewModel. No business logic
  in composables. Every screen composable has a `@Preview`.
- **Navigation:** Navigation 3. Each feature defines a `@Serializable` `NavKey` and an
  `EntryProviderScope<NavKey>.xxxEntry()` function; `:app` registers it in `AppNavDisplay`.
  Features navigate through lambda parameters (`onOpenDetail: (id) -> Unit`), never by touching
  the back stack themselves.
- **Results and text:** wrap operation results in `Resource`; pass user-facing text as `UiText`
  instead of holding a `Context`.
- **No hardcoded user-facing text:** every string the user sees lives in the `strings.xml` of the
  module that uses it, prefixed with the module name (`profile_title`, `ui_retry`), and is
  referenced through `stringResource` or `UiText.StringResource`.
- **Dependency injection:** everything is provided through Hilt. Do not create dependencies by hand
  inside classes.
- **Build configuration:** shared module setup lives in the convention plugins in `build-logic/`;
  a module's `build.gradle.kts` only applies plugins and lists its own dependencies. Declare all
  library versions in `gradle/libs.versions.toml` (and see "Ask before adding a library" above).

### Adding a feature module

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

---

## 9. Git

Follow the choice recorded in Project Settings:

- **`AI`:** create branches, commit, and push as described below.
- **`Developer`:** do not commit or push. When a piece of work is finished, suggest a branch name
  and a commit message for the developer to use.

### Branches

- `main` is always stable and releasable. Never commit to it directly.
- `develop` is the integration branch.
- Work happens on short-lived branches cut from `develop`:
  - `feature/<short-description>` for new functionality
  - `bugfix/<short-description>` for fixes
  - `refactor/<short-description>`, `chore/<short-description>`, `docs/<short-description>` as fitting
- `hotfix/<short-description>` is cut from `main` for urgent production fixes.
- `release/<version>` is cut from `develop` when preparing a release.

### Commits

Use [Conventional Commits](https://www.conventionalcommits.org):

```text
<type>(<scope>): <short summary in imperative mood>
```

Types: `feat`, `fix`, `refactor`, `test`, `docs`, `chore`, `style`, `perf`, `build`, `ci`.

Example: `feat(profile): add profile screen with pull to refresh`

- One logical change per commit. Keep commits small and buildable.
- Never commit secrets, keys, or `local.properties`.
- Never force-push shared branches or rewrite published history.
