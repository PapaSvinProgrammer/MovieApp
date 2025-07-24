plugins {
    id("android-feature-module")
    id("kotlin-kapt")
}

dependencies {
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}
