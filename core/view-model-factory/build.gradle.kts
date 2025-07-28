plugins {
    id("android-core-module")
    id("kotlin-kapt")
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}