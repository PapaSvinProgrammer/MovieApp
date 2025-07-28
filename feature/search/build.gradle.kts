plugins {
    id("android-feature-module")
}

dependencies {
    implementation(project(":domain:person"))
    implementation(project(":domain:movie"))
    implementation(project(":domain:collection-use-case"))
    implementation(project(":core:ui"))
    api(project(":core:core-component"))
    implementation(libs.androidx.paging.compose)
}