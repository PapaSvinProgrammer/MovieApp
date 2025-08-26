plugins {
    id("android-feature-module")
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(project(":core:ui"))
}