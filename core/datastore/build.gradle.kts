// OPTIONAL MODULE — delete :core:datastore if the project does not store key-value settings.
// See "Removing an optional module" in SETUP.md.
//
// Key-value storage with Jetpack DataStore (Preferences), for settings and small flags. Put data
// sources that read and write the preferences here; repositories in :core:data use them.
plugins {
    alias(libs.plugins.starter.android.library)
    alias(libs.plugins.starter.hilt)
}

android {
    namespace = "com.template.androidstarter.core.datastore"
}

dependencies {
    api(libs.androidx.datastore.preferences)
}
