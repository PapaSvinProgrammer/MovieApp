plugins {
    id("android-feature-module")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":core:data"))
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}
