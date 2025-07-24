plugins {
    id("android-domain-module")
}

dependencies {
    api(project(":core:data"))
    implementation(libs.androidx.paging.runtime.ktx)
    testImplementation(libs.junit)
}