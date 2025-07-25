plugins {
    id("android-feature-module")
}

dependencies {
    implementation(project(":domain:person"))
    implementation(project(":domain:movie"))
    implementation(project(":domain:collectionUseCase"))
    implementation(project(":core:ui"))
    api(project(":core:coreComponent"))
    implementation(libs.androidx.paging.compose)
}