plugins {
    id("android-domain-module")
}

dependencies {
    api(project(":core:data"))
    testImplementation(libs.junit)
}