plugins {
    id("android-feature-module")
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:security"))
    implementation(project(":core:data"))
}
