plugins {
    id("android-feature-module")
}

dependencies {
    implementation(project(":feature:person-list-view-model"))
    implementation(project(":core:ui"))
}