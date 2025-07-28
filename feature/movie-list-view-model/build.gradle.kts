plugins {
    id("android-feature-module")
}

dependencies {
    api(project(":core:core-component"))
    api(project(":domain:movie"))
    api(project(":core:ui"))
}