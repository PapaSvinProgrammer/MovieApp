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
include(":domain:collection")
include(":domain:comment")
include(":domain:movie")
include(":domain:person")
include(":domain:personAwards")
include(":domain:searchHistory")
include(":domain:studio")
include(":feature")
include(":core:navigationRoute")
include(":feature:aboutApp")
include(":feature:account")
include(":feature:awardListScreen")
include(":feature:collectionList")
include(":feature:login")
include(":feature:home")
include(":feature:search")
include(":feature:favorite")
include(":feature:movieList")
include(":feature:movie")
include(":feature:personScreen")
include(":feature:personScreen")
include(":feature:person")
include(":feature:personPodium")
include(":feature:settings")
include(":feature:awardList")
include(":feature:movieListViewModel")
include(":feature:personListViewModel")
