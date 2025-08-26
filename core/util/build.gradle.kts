plugins {
    id("android-core-module")
}

dependencies {
    api(project(":core:model"))

    api(libs.dagger)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}