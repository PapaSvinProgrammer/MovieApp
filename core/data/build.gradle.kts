plugins {
    id("android-core-module")
    id("kotlin-kapt")
}

dependencies {
    api(projects.core.network)
    api(projects.core.room)
    implementation(projects.core.domain)

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.bundles.paging)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}