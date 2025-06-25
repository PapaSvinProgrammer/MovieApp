package com.example.navigationroute

sealed interface NavRoute

@Serializable
data object StartRoute: NavRoute

@Serializable
data object HomeRoute: NavRoute

@Serializable
data object AccountRoute: NavRoute

@Serializable
data object SearchRoute: NavRoute

@Serializable
data object FavoriteRoute: NavRoute

@Serializable
data object SettingsRoute: NavRoute

@Serializable
data object AboutAppRoute: NavRoute

@Serializable
data object SearchSettingsRoute: NavRoute

@Serializable
data class SearchResultRoute(
    val queryParameters: List<Pair<String, String>>
): NavRoute

@Serializable
data class CollectionListRoute(
    val category: String? = null
): NavRoute

@Serializable
data class MovieListRoute(
    val title: String,
    val queryParameters: List<Pair<String, String>>
): NavRoute

@Serializable
data class HomeDetailListRoute(
    val title: String,
    val queryParameters: List<Pair<String, String>>
): NavRoute

@Serializable
data class PersonPodiumListRoute(
    val title: String,
    val queryParameters: List<Pair<String, String>>
): NavRoute

@Serializable
data class PersonRoute(
    val id: Int
): NavRoute

@Serializable
data class PersonDetailRoute(
    val id: Int
): NavRoute

@Serializable
data class AwardListRoute(
    val id: Int,
    val isMovie: Boolean
): NavRoute

@Serializable
data class MovieRoute(
    val id: Int
): NavRoute