plugins {
    id("android-feature-module")
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(projects.core.ui)
    implementation(projects.core.security)
    implementation(projects.core.network)

    implementation(libs.vkid)
    implementation(libs.ktor.client.core)
}