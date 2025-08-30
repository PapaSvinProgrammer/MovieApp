plugins {
    id("android-core-module")
    alias(libs.plugins.ksp)
    id("kotlin-kapt")
}

dependencies {
    implementation(projects.core.util)
    implementation(projects.core.model)
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.paging)
    ksp(libs.androidx.room.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}