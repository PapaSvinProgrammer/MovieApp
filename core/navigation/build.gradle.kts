plugins {
    id("android-core-module")
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(project(":core:model"))
    implementation(libs.haze)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.navigation)
}
