plugins {
    id("android-core-module")
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.util)

    implementation(libs.bundles.paging)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}