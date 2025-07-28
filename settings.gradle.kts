@file:Suppress("UnstableApiUsage")

include(":feature:otp-screen")


include(":core:coreComponent")
include(":core:security")

include(":core:navigation")


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
include(":core:utils")
include(":core:network")
include(":core:data")
include(":domain")
include(":domain:comment")
include(":domain:movie")
include(":domain:person")
include(":domain:awards")
include(":domain:studio")
include(":feature")
include(":core:navigationRoute")
include(":feature:about-app")
include(":feature:account")
include(":feature:collection-list")
include(":feature:login")
include(":feature:home")
include(":feature:search")
include(":feature:favorite")
include(":feature:movie-list")
include(":feature:movie-screen")
include(":feature:person-podium")
include(":feature:settings")
include(":feature:award-list")
include(":feature:movie-list-view-model")
include(":feature:person-list-view-model")
include(":core:ui")
include(":core:room")
include(":feature:person-screen")
include(":domain:collection-use-case")
include(":core:viewmodelfactory")
