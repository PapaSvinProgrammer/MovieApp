@file:Suppress("UnstableApiUsage")

import java.net.URI

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":core:domain")
include(":core:base-view-models")
include(":feature:otp-screen")
include(":core:security")

includeBuild("build-logic")

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://artifactory-external.vkpartner.ru/artifactory/vkid-sdk-android/")
        maven(url = "https://artifactory-external.vkpartner.ru/artifactory/maven/")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = URI("https://artifactory-external.vkpartner.ru/artifactory/vkid-sdk-android/")
        }
        maven {
            url = URI("https://artifactory-external.vkpartner.ru/artifactory/maven/")
        }
        maven {
            url =
                URI("https://artifactory-external.vkpartner.ru/artifactory/vk-id-captcha/android/")
        }
    }
}

rootProject.name = "MovieApp"
include(":app")
include(":core")
include(":core:model")
include(":core:util")
include(":core:network")
include(":core:data")
include(":feature")
include(":core:navigation")
include(":feature:about-app")
include(":feature:account")
include(":feature:collection-list")
include(":feature:login")
include(":feature:home")
include(":feature:search")
include(":feature:favorite")
include(":feature:movie-list")
include(":feature:movie")
include(":feature:person-podium")
include(":feature:settings")
include(":feature:award-list")
include(":core:ui")
include(":core:room")
include(":feature:person")
include(":core:view-model-factory")
