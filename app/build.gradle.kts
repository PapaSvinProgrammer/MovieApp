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
    implementation(project(":feature:aboutApp"))
    implementation(project(":feature:account"))
    implementation(project(":feature:awardList"))
    implementation(project(":feature:collectionList"))
    implementation(project(":feature:favorite"))
    implementation(project(":feature:home"))
    implementation(project(":feature:login"))
    implementation(project(":feature:movieList"))
    implementation(project(":feature:movieScreen"))
    implementation(project(":feature:personPodium"))
    implementation(project(":feature:personScreen"))
    implementation(project(":feature:search"))
    implementation(project(":feature:settings"))
    implementation(project(":core:ui"))
    implementation(project(":core:data"))
    implementation(project(":core:network"))
    implementation(project(":core:room"))
    implementation(project(":core:navigationRoute"))
    implementation(project(":core:viewModelFactory"))

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.ui.test.junit4)
}