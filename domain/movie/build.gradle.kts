plugins {
    id("android-domain-module")
}

dependencies {
    implementation(project(":core:common"))
    api(project(":core:data"))
    testImplementation(libs.junit)
}