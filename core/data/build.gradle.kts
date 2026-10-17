// Data layer: repository implementations (repository/) that combine the network, database, and
// datastore sources and map their types to domain models. Bound to the domain interfaces in
// di/RepositoryModule.
//
// If the project drops :core:network, :core:database, or :core:datastore, remove the matching
// line below as well.
plugins {
    alias(libs.plugins.starter.android.library)
    alias(libs.plugins.starter.hilt)
}

android {
    namespace = "com.template.androidstarter.core.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.network)
    implementation(projects.core.database)
    implementation(projects.core.datastore)

    testImplementation(projects.core.testing)
}
