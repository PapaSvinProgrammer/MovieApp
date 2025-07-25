plugins {
    id("android-feature-module")
}

dependencies {
    api(project(":core:coreComponent"))
    api(project(":domain:awards"))
    implementation(project(":core:ui"))
    implementation(project(":core:viewModelFactory"))
}