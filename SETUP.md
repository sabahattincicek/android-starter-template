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
  folder in `data/schemas`;
* sets `rootProject.name` in `settings.gradle.kts`;
* replaces the app name in every string resource that shows it (`app_name`);
* deletes the `build` folders of the modules, because outputs built with the old package would
  break the next build.

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

## 3. Remove the Optional Parts You Do Not Need

The `:data` module comes with three ready-to-use parts: **Network** (Retrofit + OkHttp), **Room**
(local database), and **DataStore** (key-value settings). Not every app needs them. Their files
start with an `OPTIONAL` note, and their lines in `data/build.gradle.kts` are marked with comments.

### Removing an optional part

| Step | Network | Room | DataStore |
|---|---|---|---|
| 1. Delete files | `data/.../di/NetworkModule.kt` | `data/.../di/DatabaseModule.kt`, the `data/.../local/database/` folder, `data/schemas/` | `data/.../di/DataStoreModule.kt` |
| 2. In `data/build.gradle.kts` remove | the `// Network` dependency block, the serialization plugin, and the `buildFeatures` / `BASE_URL` block | the `// Room` dependency block, the room plugin, and the `room { }` block | the `// DataStore` dependency line |
| 3. Extra cleanup | `INTERNET` permission in `app/src/main/AndroidManifest.xml` | `alias(libs.plugins.room)` in the root `build.gradle.kts` | — |

Keep the empty `remote/` or `local/` folders if you like; their `.gitkeep` files are harmless.
Optionally, remove the now unused entries from `gradle/libs.versions.toml` (`retrofit`, `okhttp`,
`room`, `datastore`). Keep `kotlinx-serialization`: Navigation 3 keys use it.

Then sync Gradle and build again.

---

## 4. Replace the Placeholders

* **Start screen:** the app opens a placeholder screen that shows the app name
  (`app/.../navigation/StartPlaceholder.kt`). Add your first feature (see "Adding a feature
  module" in `AGENTS.md`), start the back stack in `AppNavDisplay` with its key, and delete the
  placeholder.
* **`ExampleEntity` / `ExampleDao`** in `data/.../local/database/`: Room needs at least one entity,
  so replace them with your first real table instead of just deleting them. Delete `data/schemas/`
  as well so the schema starts fresh at version 1.
* **Empty folders** contain a `.gitkeep` file so that git keeps them. Delete the `.gitkeep` once a
  folder has real files, or delete the folder if the project does not need it.

---

## 5. Review the AI Assistant Files

The template ships with three files that guide AI coding assistants. Open them and adjust them to
your project:

| File | Purpose |
|---|---|
| `AGENTS.md` | Rules the AI follows: scope, comments, logging, unit tests, architecture, and git workflow. |
| `DEVLOG.md` | Timestamped log of what has been done, newest entry first, so the AI does not have to re-read the whole codebase every session. |
| `ROADMAP.md` | Planned work, split into phases and checkbox tasks (`[ ]` open, `[x]` done). |
| `docs/PROJECT_DISCOVERY.md` | The questions the AI asks to write the app plan and the design criteria. |

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

When the setup is finished, the AI runs the **project discovery** interview from
`docs/PROJECT_DISCOVERY.md`. It asks you, a few questions at a time, about the app (purpose,
users, scope, platforms, backend and hosting, integrations, monetization, release plan) and then
about its design (personality, colors, typography, shapes, components, layout, motion,
accessibility, tone). From your answers it writes:

* `docs/APP_PLAN.md`: the app plan, from the first release to the long-term vision.
* `docs/DESIGN.md`: the design system the Compose theme is built from.
* `docs/design.html`: a single page showing the palette, components, and screen mockups. Open it in
  a browser.

You can answer any question with "you decide" or "not sure yet". The AI then makes a choice and
explains it, or records the question as open. It also rebuilds `ROADMAP.md` from the plan.

---

## 6. Remove the Setup Files

After completing the setup successfully:

* Delete this file, `SETUP.md`, and the "First-Time Setup" section of `README.md`.
* Keep `docs/PROJECT_DISCOVERY.md`: the AI uses it again when you want to revisit the plan or the
  design.
* Optionally remove the rename task: delete `alias(libs.plugins.starter.rename)` from the root
  `build.gradle.kts`, the `starter-rename` line in `gradle/libs.versions.toml`, the `rename` entry in
  `build-logic/convention/build.gradle.kts`, and
  `build-logic/convention/src/main/kotlin/RenameProjectConventionPlugin.kt`.

The project is now ready for development.
