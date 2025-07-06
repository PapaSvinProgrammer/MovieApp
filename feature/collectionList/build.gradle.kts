plugins {
    id("android-feature-module")
}

dependencies {
    implementation(project(":core:navigationRoute"))
    implementation(project(":core:ui"))
    implementation(project(":core:viewModelFactory"))
    api(project(":domain:collectionUseCase"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}