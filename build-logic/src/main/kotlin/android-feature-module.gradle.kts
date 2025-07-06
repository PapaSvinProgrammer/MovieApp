import com.android.build.gradle.LibraryExtension
import gradle.kotlin.dsl.accessors._7a1e42e81073b4d267281119d0f1f871.implementation
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

plugins {
    id("android-base")
    id("com.android.library")
    id("org.jetbrains.kotlin.plugin.compose")
    id("kotlin-kapt")
}

configure<LibraryExtension> {
    baseAndroidConfig(project)
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {
    implementation(libs.coil.coil.svg)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    implementation(libs.haze)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation)
    implementation(libs.material)
}
