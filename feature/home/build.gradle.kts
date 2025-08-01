plugins {
    id("android-feature-module")
}

dependencies {
    implementation(project(":feature:movie-list-view-model"))
    implementation(project(":domain:collection-use-case"))
    implementation(project(":domain:movie"))
    implementation(project(":core:ui"))
}