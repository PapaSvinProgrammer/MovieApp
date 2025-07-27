plugins {
    id("android-feature-module")
    id("kotlin-kapt")
}

dependencies {
    api(project(":core:data"))
    api(project(":core:security"))
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}
