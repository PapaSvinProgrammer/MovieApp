plugins {
    id("android-feature-module")
    alias(libs.plugins.kotlin.serialization)
    id("vkid.manifest.placeholders")
}

dependencies {
    implementation(projects.core.security)

    implementation(libs.vkid)
    implementation(libs.authsdk)
    implementation(projects.core.ui)
    implementation(libs.ktor.client.core)
}