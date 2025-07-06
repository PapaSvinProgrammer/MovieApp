plugins {
    id("android-feature-module")
}

dependencies {
    api(project(":domain:movie"))
    implementation(project(":core:ui"))
    implementation(project(":core:viewModelFactory"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}