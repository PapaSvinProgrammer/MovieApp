plugins {
    id("android-feature-module")
}

dependencies {
    api(project(":core:core-component"))
    implementation(project(":feature:movie-list-view-model"))
    implementation(project(":domain:collection-use-case"))
    implementation(project(":domain:movie"))
    implementation(project(":core:ui"))
}