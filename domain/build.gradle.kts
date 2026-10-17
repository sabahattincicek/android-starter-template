// Domain layer: the business rules of the app, in plain Kotlin.
//
//   model/        business models
//   repository/   repository interfaces (implemented in :data)
//   usecase/      use cases, one action each, called by the ViewModels
//
// Depends only on :core:common. :data implements it, the presentation modules call it.
plugins {
    alias(libs.plugins.starter.android.library)
    alias(libs.plugins.starter.hilt)
}

android {
    namespace = "com.template.androidstarter.domain"
}

dependencies {
    api(projects.core.common)

    testImplementation(projects.core.testing)
}
