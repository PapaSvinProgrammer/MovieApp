plugins {
    id("android-domain-module")
}

dependencies {
    implementation(project(":core:common"))
    api(project(":core:data"))
    implementation(libs.androidx.paging.runtime.ktx)
    testImplementation(libs.junit)
}