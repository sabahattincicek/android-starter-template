# Starter Template Setup Guide

Follow these steps immediately after creating a new project from this template.

---

## 1. Rename the Project

The template ships with the package `com.template.androidstarter`, the project name
`AndroidStarter`, and the app name `Android Starter`. One Gradle task renames all of them:

```bash
./gradlew renameProject -PnewPackage=com.mycompany.mynewapp -PnewProjectName=MyNewApp
```

In PowerShell, quote the arguments:

```powershell
.\gradlew renameProject "-PnewPackage=com.mycompany.mynewapp" "-PnewProjectName=MyNewApp"
```

Optional: `-PnewAppName="My New App"` sets the name shown under the launcher icon. Without it, the
project name is split into words (`MyNewApp` → `My New App`).

The task:

* replaces the package in every Kotlin, Gradle, XML, and TOML file: `namespace`, `applicationId`,
  `package` and `import` lines in all modules;
* moves the source folders of every module to the new package path, and renames the Room schema
  folder in `core/database/schemas`;
* sets `rootProject.name` in `settings.gradle.kts`;
* replaces the app name in every string resource that shows it (`app_name`, the home screen title).

Package rules: lowercase, at least two segments, no underscores (`com.mycompany.mynewapp`).

The `App` class, `AppTheme`, and `Theme.App` are intentionally generic and do not need to be renamed.

---

## 2. Verify the Project

Close and reopen the project in Android Studio (or run **File → Sync Project with Gradle Files**),
then:

* [ ] Search the project for `androidstarter` and `AndroidStarter`. The only expected hits are in
      `build-logic/` (the rename task itself), `DEVLOG.md`, and this file.
* [ ] Check `applicationId` in `app/build.gradle.kts`.
* [ ] Run `./gradlew assembleDebug testDebugUnitTest lint`.
* [ ] Run the app and make sure it launches.

---

## 3. Remove the Optional Modules You Do Not Need

`:core:network` (Retrofit), `:core:database` (Room), and `:core:datastore` (DataStore) are ready to
use, but not every app needs them. Each of their files starts with an `OPTIONAL` note.

### Removing an optional module

| Step | `:core:network` | `:core:database` | `:core:datastore` |
|---|---|---|---|
| 1. Delete the folder | `core/network` | `core/database` | `core/datastore` |
| 2. Remove from `settings.gradle.kts` | `include(":core:network")` | `include(":core:database")` | `include(":core:datastore")` |
| 3. Remove from `core/data/build.gradle.kts` | `implementation(projects.core.network)` | `implementation(projects.core.database)` | `implementation(projects.core.datastore)` |
| 4. Extra cleanup | `INTERNET` permission in `app/src/main/AndroidManifest.xml` | `alias(libs.plugins.room)` in the root `build.gradle.kts` | — |

Optionally, remove the now unused entries from `gradle/libs.versions.toml` (`retrofit`, `okhttp`,
`room`, `datastore`). Keep `kotlinx-serialization`: Navigation 3 keys use it.

Then sync Gradle and build again.

---

## 4. Replace the Example Code

* **`:feature:home`** is an example screen (a tap counter) that shows how a feature is laid out.
  Turn it into the real start screen, or add your own feature (see "Adding a feature module" in
  `AGENTS.md`) and delete it. When deleting it, also remove it from `settings.gradle.kts`,
  `app/build.gradle.kts`, and `AppNavDisplay`.
* **`ExampleEntity` / `ExampleDao`** in `:core:database`: Room needs at least one entity, so replace
  them with your first real table instead of just deleting them. Delete
  `core/database/schemas` as well so the schema starts fresh at version 1.

---

## 5. Review the AI Assistant Files

The template ships with three files that guide AI coding assistants. Open them and adjust them to
your project:

| File | Purpose |
|---|---|
| `AGENTS.md` | Rules the AI follows: scope, comments, logging, unit tests, architecture, and git workflow. |
| `DEVLOG.md` | Timestamped log of what has been done, newest entry first, so the AI does not have to re-read the whole codebase every session. |
| `ROADMAP.md` | Planned work, split into phases and checkbox tasks (`[ ]` open, `[x]` done). |

Things to do:

* [ ] Read `AGENTS.md` and change, add, or remove rules as you see fit.
* [ ] Replace the placeholder phases in `ROADMAP.md` with your own plan.
* [ ] Leave the first `DEVLOG.md` entry as it is: it describes what the template gave you, and its
      mention of `AndroidStarter` is intentional.

In your first session, tell the AI to read `AGENTS.md`. It will then ask you two things and record
your answers in the file:

* **Which AI tool you are using.** It renames `AGENTS.md` to the file name your tool loads
  automatically (for example `CLAUDE.md` for Claude Code), so later sessions pick the rules up on
  their own.
* **Who makes git commits and pushes:** the AI or you.

---

## 6. Remove the Setup Files

After completing the setup successfully:

* Delete this file, `SETUP.md`, and the "First-Time Setup" section of `README.md`.
* Optionally remove the rename task: delete `alias(libs.plugins.starter.rename)` from the root
  `build.gradle.kts`, the `starter-rename` line in `gradle/libs.versions.toml`, the `rename` entry in
  `build-logic/convention/build.gradle.kts`, and
  `build-logic/convention/src/main/kotlin/RenameProjectConventionPlugin.kt`.

The project is now ready for development.
