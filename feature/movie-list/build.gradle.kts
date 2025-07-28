plugins {
    id("android-feature-module")
}

dependencies {
    implementation(project(":feature:movie-list-view-model"))
    implementation(project(":core:ui"))
}