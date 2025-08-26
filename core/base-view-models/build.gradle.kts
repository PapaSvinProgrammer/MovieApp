plugins {
    id("android-core-module")
    id("kotlin-kapt")
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.ui)

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    implementation(libs.androidx.lifecycle.viewmodelCompose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}