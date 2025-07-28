plugins {
    id("android-feature-module")
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:ui"))
    api(project(":core:core-component"))

    implementation(libs.lottie.compose)
}