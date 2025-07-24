plugins {
    id("android-feature-module")
}

dependencies {
    implementation(project(":feature:personListViewModel"))
    implementation(project(":core:ui"))
}