plugins {
    id("android-feature-module")
}

dependencies {
    api(project(":core:coreComponent"))
    api(project(":domain:movie"))
    api(project(":core:ui"))
}