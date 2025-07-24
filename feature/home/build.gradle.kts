plugins {
    id("android-feature-module")
}

dependencies {
    implementation(project(":feature:movieListViewModel"))
    implementation(project(":domain:collectionUseCase"))
    implementation(project(":domain:movie"))
    implementation(project(":core:ui"))
}