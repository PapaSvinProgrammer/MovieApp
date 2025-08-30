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
    api(projects.core.model)
    api(projects.core.util)
    api(projects.core.navigation)
    api(projects.core.viewModelFactory)

    implementation(libs.haze)
    implementation(libs.haze.materials)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.ktor)
    implementation(libs.bundles.compose)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.navigation)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}