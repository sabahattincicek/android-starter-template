import com.android.build.api.dsl.ApplicationExtension
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
 * Configures the `:app` module: Android application plugin, SDK levels, Java version, BuildConfig,
 * Timber, and the unit test libraries.
 */
class AndroidApplicationConventionPlugin : Plugin<Project> {

    /** Applies the Android application plugin and the shared configuration to [target]. */
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("com.android.application")

        extensions.configure<ApplicationExtension> {
            compileSdk = libs.intVersion("compileSdk")
            defaultConfig {
                minSdk = libs.intVersion("minSdk")
                targetSdk = libs.intVersion("targetSdk")
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
            compileOptions {
                sourceCompatibility = JAVA_VERSION
                targetCompatibility = JAVA_VERSION
            }
            buildFeatures {
                buildConfig = true
            }
        }

        dependencies {
            add("implementation", libs.library("timber"))
        }
        addUnitTestDependencies()
    }
}
