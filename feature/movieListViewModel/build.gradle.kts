plugins {
    id("android-feature-module")
}

dependencies {
    implementation(project(":domain:movie"))
    implementation(project(":core:ui"))
}