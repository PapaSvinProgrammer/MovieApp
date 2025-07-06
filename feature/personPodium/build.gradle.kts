plugins {
    id("android-feature-module")
}

dependencies {
    api(project(":feature:personListViewModel"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigationRoute"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}