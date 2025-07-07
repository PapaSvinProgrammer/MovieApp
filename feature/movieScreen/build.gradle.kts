plugins {
    id("android-feature-module")
}

dependencies {
    api(project(":domain:movie"))
    implementation(project(":domain:comment"))
    implementation(project(":domain:collectionUseCase"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigationRoute"))
    implementation(project(":core:viewModelFactory"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}