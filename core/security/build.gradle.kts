plugins {
    id("android-core-module")
    id("kotlin-kapt")
}

dependencies {
    implementation(projects.core.util)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}