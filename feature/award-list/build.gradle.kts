plugins {
    id("android-feature-module")
}

dependencies {
    api(project(":core:core-component"))
    api(project(":domain:awards"))
    implementation(project(":core:ui"))
    implementation(project(":core:view-model-factory"))
}