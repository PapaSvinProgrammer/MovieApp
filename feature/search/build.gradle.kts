plugins {
    id("android-feature-module")
}

dependencies {
    api(project(":domain:person"))
    api(project(":domain:movie"))
    api(project(":domain:searchHistory"))
    api(project(":domain:collectionUseCase"))
    implementation(project(":core:navigationRoute"))
    implementation(project(":core:ui"))
    implementation(project(":core:viewModelFactory"))

    implementation(libs.androidx.paging.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}