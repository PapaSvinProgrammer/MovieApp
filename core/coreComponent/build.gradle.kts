plugins {
    id("android-feature-module")
    id("kotlin-kapt")
}

dependencies {
    api(project(":core:data"))
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}
