@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

plugins {
    id("android-base")
    id("com.android.application")
    id("org.jetbrains.kotlin.plugin.compose")
    id("kotlin-kapt")
}

configure<BaseAppModuleExtension> {
    baseAndroidConfig(project)
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    androidResources {
        generateLocaleConfig = true
    }
}

dependencies {
    implementation(libs.haze)
    implementation(libs.haze.materials)
    implementation(libs.bundles.ktor)
    implementation(libs.bundles.compose)
    implementation(libs.androidx.navigation)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
}
