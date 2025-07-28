plugins {
    id("android-feature-module")
}

dependencies {
    api(project(":core:core-component"))
    implementation(project(":core:ui"))
    implementation(project(":domain:collection-use-case"))
}