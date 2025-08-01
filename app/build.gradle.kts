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
    implementation(project(":feature:about-app"))
    implementation(project(":feature:account"))
    implementation(project(":feature:award-list"))
    implementation(project(":feature:collection-list"))
    implementation(project(":feature:favorite"))
    implementation(project(":feature:home"))
    implementation(project(":feature:login"))
    implementation(project(":feature:movie-list"))
    implementation(project(":feature:movie-screen"))
    implementation(project(":feature:person-podium"))
    implementation(project(":feature:person-screen"))
    implementation(project(":feature:search"))
    implementation(project(":feature:settings"))
    implementation(project(":feature:otp-screen"))
    implementation(project(":feature:movie-list-view-model"))
    implementation(project(":feature:person-list-view-model"))
    implementation(project(":core:navigation-route"))
    implementation(project(":core:data"))
    implementation(project(":core:ui"))
    implementation(project(":core:security"))
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.ui.test.junit4)
}