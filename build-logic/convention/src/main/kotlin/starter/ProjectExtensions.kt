package starter

import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.getByType

/*
 * Helpers shared by the convention plugins: access to the version catalog and the values every
 * module agrees on.
 */

/** Java version that all modules compile against and target. */
val JAVA_VERSION: JavaVersion = JavaVersion.VERSION_17

/** The `libs` version catalog of the main build. */
val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

/** Looks up a library alias from the catalog, failing with a clear message if it is missing. */
fun VersionCatalog.library(alias: String): Provider<MinimalExternalModuleDependency> =
    findLibrary(alias).orElseThrow { IllegalStateException("Library '$alias' is missing in libs.versions.toml") }

/** Reads a version from the catalog as an integer, used for the SDK levels. */
fun VersionCatalog.intVersion(alias: String): Int =
    findVersion(alias).orElseThrow { IllegalStateException("Version '$alias' is missing in libs.versions.toml") }
        .requiredVersion.toInt()

/** Adds the unit test libraries every module uses. */
fun Project.addUnitTestDependencies() {
    dependencies.apply {
        add("testImplementation", libs.library("junit"))
        add("testImplementation", libs.library("mockk"))
        add("testImplementation", libs.library("kotlinx-coroutines-test"))
        add("testImplementation", libs.library("turbine"))
    }
}
