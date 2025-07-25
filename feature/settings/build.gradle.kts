plugins {
    id("android-feature-module")
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:ui"))
    api(project(":core:coreComponent"))
}