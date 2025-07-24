plugins {
    id("android-feature-module")
}

dependencies {
    implementation(project(":feature:movieListViewModel"))
    implementation(project(":core:ui"))
}