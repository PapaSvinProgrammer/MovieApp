plugins {
    id("android-core-module")
    alias(libs.plugins.kotlin.serialization)
    id("kotlin-kapt")
}

android {
    defaultConfig {
        buildConfigField(
            type = "String",
            name = "MOVIE_API_KEY",
            value = "\"${rootProject.extra["movieApiKey"]}\""
        )
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    api(project(":core:model"))
    api(project(":core:common"))

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    api(libs.bundles.ktor)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}