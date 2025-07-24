plugins {
    id("android-core-module")
}

dependencies {
    api(project(":core:model"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}