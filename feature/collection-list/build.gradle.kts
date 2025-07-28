plugins {
    id("android-feature-module")
}

dependencies {
    api(project(":core:coreComponent"))
    implementation(project(":core:ui"))
    implementation(project(":domain:collection-use-case"))
}