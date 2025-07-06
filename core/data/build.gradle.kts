plugins {
    id("android-core-module")
    id("kotlin-kapt")
}

dependencies {
    api(project(":core:model"))
    api(project(":core:network"))
    api(project(":core:room"))

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.bundles.paging)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}