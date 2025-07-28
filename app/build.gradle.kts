plugins {
    id("android-app-module")
    alias(libs.plugins.graph)
}

android {
    defaultConfig {
        applicationId = Const.NAMESPACE
        versionCode = 1
        versionName = "1.0"
        targetSdk = Const.COMPILE_SKD
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":core:core-component"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.ui.test.junit4)
}