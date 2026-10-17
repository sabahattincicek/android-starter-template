import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import starter.libs
import starter.library

/**
 * Sets up Hilt dependency injection in a module: the KSP and Hilt plugins, the Hilt runtime, and
 * its annotation processor.
 */
class HiltConventionPlugin : Plugin<Project> {

    /** Applies KSP and Hilt to [target] and adds the Hilt libraries. */
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("com.google.devtools.ksp")
        pluginManager.apply("com.google.dagger.hilt.android")

        dependencies {
            add("implementation", libs.library("hilt-android"))
            add("ksp", libs.library("hilt-compiler"))
        }
    }
}
