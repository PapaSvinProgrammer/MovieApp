plugins {
    id("android-core-module")
}

dependencies {
    api(project(":core:model"))
    implementation(libs.dagger)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}