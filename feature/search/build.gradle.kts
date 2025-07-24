plugins {
    id("android-feature-module")
}

dependencies {
    implementation(project(":domain:person"))
    implementation(project(":domain:movie"))
    implementation(project(":domain:searchHistory"))
    implementation(project(":domain:collectionUseCase"))
    implementation(project(":core:ui"))
    implementation(libs.androidx.paging.compose)
}