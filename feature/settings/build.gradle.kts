plugins {
    id("android-feature-module")
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:ui"))

    implementation(libs.lottie.compose)
}