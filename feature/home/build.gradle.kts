plugins {
    id("android-feature-module")
}

dependencies {
    api(project(":core:coreComponent"))
    implementation(project(":feature:movieListViewModel"))
    implementation(project(":domain:collectionUseCase"))
    implementation(project(":domain:movie"))
    implementation(project(":core:ui"))
}