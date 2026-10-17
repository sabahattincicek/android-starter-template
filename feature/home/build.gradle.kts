// EXAMPLE FEATURE — the home screen, showing how a feature module is laid out (navigation key,
// stateful route, stateless screen, ViewModel, UI state, events, and a ViewModel test). Copy it for
// new features; replace or delete it once the real start screen exists.
plugins {
    alias(libs.plugins.starter.android.feature)
}

android {
    namespace = "com.template.androidstarter.feature.home"
}
