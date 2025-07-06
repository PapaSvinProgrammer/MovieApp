plugins {
    id("android-feature-module")
}

dependencies {
    api(project(":feature:movieListViewModel"))
    implementation(project(":core:navigationRoute"))
    implementation(project(":core:ui"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}