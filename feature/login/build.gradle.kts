plugins {
    id("android-feature-module")
}

dependencies {
    api(project(":core:data"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigationRoute"))
    implementation(project(":core:viewModelFactory"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}