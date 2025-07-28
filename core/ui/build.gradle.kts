plugins {
    id("android-core-module")
    alias(libs.plugins.kotlin.compose)
}

android {
    buildFeatures {
        compose = true
    }
}

dependencies {
    api(project(":core:model"))
    api(project(":core:utils"))
    api(project(":core:navigationRoute"))
    api(project(":core:viewmodelfactory"))
    api(project(":core:coreComponent"))

    implementation(libs.haze)
    implementation(libs.haze.materials)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation(libs.bundles.compose)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.navigation)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}