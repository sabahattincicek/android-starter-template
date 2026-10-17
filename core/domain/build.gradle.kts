// Domain layer: repository interfaces (repository/) and use cases (usecase/). Depends only on
// :core:model and :core:common; features call it, :core:data implements it.
plugins {
    alias(libs.plugins.starter.android.library)
    alias(libs.plugins.starter.hilt)
}

android {
    namespace = "com.template.androidstarter.core.domain"
}

dependencies {
    api(projects.core.model)
    api(projects.core.common)

    testImplementation(projects.core.testing)
}
