import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import starter.libs
import starter.library

/**
 * Configures a `:feature:*` module: an Android library with Compose, Hilt, and serializable
 * Navigation 3 keys, depending on the core modules every screen needs.
 *
 * Features depend on `:core:domain` and never on `:core:data`, so screens can only reach data
 * through use cases.
 */
class AndroidFeatureConventionPlugin : Plugin<Project> {

    /** Applies the library, Compose, Hilt, and serialization plugins and the feature dependencies to [target]. */
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("starter.android.library")
        pluginManager.apply("starter.android.compose")
        pluginManager.apply("starter.hilt")
        pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")

        dependencies {
            add("implementation", project(":core:common"))
            add("implementation", project(":core:model"))
            add("implementation", project(":core:domain"))
            add("implementation", project(":core:ui"))

            add("implementation", libs.library("androidx-lifecycle-runtime-compose"))
            add("implementation", libs.library("androidx-lifecycle-viewmodel-compose"))
            add("implementation", libs.library("hilt-lifecycle-viewmodel-compose"))
            add("implementation", libs.library("androidx-navigation3-runtime"))

            add("testImplementation", project(":core:testing"))
        }
    }
}
