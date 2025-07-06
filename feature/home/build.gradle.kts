plugins {
    id("android-feature-module")
}

dependencies {
    api(project(":domain:collectionUseCase"))
    implementation(project(":core:navigationRoute"))
    implementation(project(":core:viewModelFactory"))
    implementation(project(":feature:movieListViewModel"))
    implementation(project(":domain:movie"))
    implementation(project(":core:ui"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}