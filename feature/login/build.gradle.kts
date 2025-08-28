plugins {
    id("android-feature-module")
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(libs.authsdk)
    implementation(projects.core.ui)
    implementation(libs.ktor.client.core)
}