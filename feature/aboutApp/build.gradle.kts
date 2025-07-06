plugins {
    id("android-feature-module")
}

dependencies {
    implementation(project(":core:ui"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}