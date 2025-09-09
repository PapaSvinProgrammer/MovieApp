import org.gradle.internal.extensions.core.extra

plugins {
    id("android-app-module")
    alias(libs.plugins.graph)
    id("vkid.manifest.placeholders")
}

android {
    defaultConfig {
        applicationId = Const.NAMESPACE
        versionCode = 1
        versionName = "1.0"
        targetSdk = Const.COMPILE_SKD

        manifestPlaceholders["YANDEX_CLIENT_ID"] = rootProject.extra["yandexAuthKey"] ?: ""
    }

    android {
        compileOptions {
            isCoreLibraryDesugaringEnabled = true
        }
    }
}

dependencies {
    implementation(projects.feature.aboutApp)
    implementation(projects.feature.account)
    implementation(projects.feature.awardList)
    implementation(projects.feature.collectionList)
    implementation(projects.feature.favorite)
    implementation(projects.feature.home)
    implementation(projects.feature.login)
    implementation(projects.feature.movieList)
    implementation(projects.feature.movie)
    implementation(projects.feature.personPodium)
    implementation(projects.feature.person)
    implementation(projects.feature.search)
    implementation(projects.feature.settings)
    implementation(projects.feature.otpScreen)
    implementation(projects.feature.imagesList)

    implementation(projects.core.domain)
    implementation(projects.core.navigation)
    implementation(projects.core.data)
    implementation(projects.core.ui)
    implementation(projects.core.security)
    implementation(projects.core.baseViewModels)

    implementation(libs.coil.compose)
    implementation(libs.coil.network.ktor)
    implementation(libs.vkid)
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    coreLibraryDesugaring(libs.desugar.jdk.libs)
}