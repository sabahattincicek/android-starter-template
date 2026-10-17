import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import starter.JAVA_VERSION
import starter.addUnitTestDependencies
import starter.intVersion
import starter.libs
import starter.library

/**
 * Configures an Android library module (every `:core:*` and `:feature:*` module): SDK levels, Java
 * version, Timber, and the unit test libraries.
 */
class AndroidLibraryConventionPlugin : Plugin<Project> {

    /** Applies the Android library plugin and the shared configuration to [target]. */
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("com.android.library")

        extensions.configure<LibraryExtension> {
            compileSdk = libs.intVersion("compileSdk")
            defaultConfig {
                minSdk = libs.intVersion("minSdk")
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
            compileOptions {
                sourceCompatibility = JAVA_VERSION
                targetCompatibility = JAVA_VERSION
            }
            lint {
                targetSdk = libs.intVersion("targetSdk")
            }
            testOptions {
                targetSdk = libs.intVersion("targetSdk")
            }
        }

        dependencies {
            add("implementation", libs.library("timber"))
        }
        addUnitTestDependencies()
    }
}
