plugins {
    id("android-feature-module")
}

dependencies {
    implementation(project(":domain:awards"))
    implementation(project(":core:ui"))
    implementation(project(":core:view-model-factory"))
}