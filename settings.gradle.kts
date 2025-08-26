@file:Suppress("UnstableApiUsage")
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
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
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
