@file:Suppress("UnstableApiUsage")

include(":feature:otpScreen")


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
include(":feature:aboutApp")
include(":feature:account")
include(":feature:collectionList")
include(":feature:login")
include(":feature:home")
include(":feature:search")
include(":feature:favorite")
include(":feature:movieList")
include(":feature:movieScreen")
include(":feature:personPodium")
include(":feature:settings")
include(":feature:awardList")
include(":feature:movieListViewModel")
include(":feature:personListViewModel")
include(":core:ui")
include(":core:room")
include(":feature:personScreen")
include(":domain:collectionUseCase")
include(":core:viewModelFactory")
