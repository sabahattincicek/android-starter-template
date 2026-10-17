// OPTIONAL MODULE — delete :core:database if the project does not store data in a local database.
// See "Removing an optional module" in SETUP.md.
//
// Local database with Room: AppDatabase, entities (entity/), and DAOs (dao/). Repositories in
// :core:data map the entities to domain models.
plugins {
    alias(libs.plugins.starter.android.library)
    alias(libs.plugins.starter.hilt)
    alias(libs.plugins.room)
}

android {
    namespace = "com.template.androidstarter.core.database"
}

room {
    // Exported schemas are committed, so migrations can be written and tested against them.
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    api(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
}
