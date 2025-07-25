plugins {
    id("android-feature-module")
}

dependencies {
    implementation(project(":domain:person"))
    implementation(project(":core:ui"))
    api(project(":core:coreComponent"))
}