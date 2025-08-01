plugins {
    id("android-feature-module")
}

dependencies {
    api(project(":domain:movie"))
    implementation(project(":domain:comment"))
    implementation(project(":domain:collection-use-case"))
    implementation(project(":core:ui"))
}