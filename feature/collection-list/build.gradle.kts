plugins {
    id("android-feature-module")
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":domain:collection-use-case"))
}