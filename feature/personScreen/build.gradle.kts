plugins {
    id("android-feature-module")
}

dependencies {
    implementation(project(":domain:awards"))
    implementation(project(":domain:movie"))
    implementation(project(":domain:person"))
    implementation(project(":core:ui"))
}