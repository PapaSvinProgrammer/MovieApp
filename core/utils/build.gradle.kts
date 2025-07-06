plugins {
    id("android-core-module")
}

dependencies {
    api(project(":core:model"))
    api(project(":core:common"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}