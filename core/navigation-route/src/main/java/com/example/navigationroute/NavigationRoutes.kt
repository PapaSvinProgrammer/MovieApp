package com.example.navigationroute

import com.example.navigationroute.model.PersonMovieScreenObject
import com.example.navigationroute.model.WatchabilityScreenObject
import kotlinx.serialization.Serializable

sealed interface NavRoute

sealed interface MainRoutes {
    @Serializable
    data object HomeRoute : NavRoute

    @Serializable
    data object AccountRoute : NavRoute

    @Serializable
    data object SearchRoute : NavRoute

    @Serializable
    data object FavoriteRoute : NavRoute
}

sealed interface SearchRoutes {
    @Serializable
    data object SearchSettingsRoute : NavRoute

    @Serializable
    data class SearchResultRoute(
        val queryParameters: List<Pair<String, String>>
    ) : NavRoute
}

sealed interface SettingsRoutes {
    @Serializable
    data object AboutAppRoute : NavRoute

    @Serializable
    data object SoundRoute : NavRoute

    @Serializable
    data object ConfidentialRoute : NavRoute

    @Serializable
    data object DataMemoryRoute : NavRoute

    @Serializable
    data object LanguageRoute : NavRoute

    @Serializable
    data object DecorRoute : NavRoute

    @Serializable
    data object SupportRoute : NavRoute
}

sealed interface MovieRoutes {
    @Serializable
    data class MovieRoute(
        val id: Int
    ) : NavRoute

    @Serializable
    data class WatchabilityListRoute(
        val watchability: WatchabilityScreenObject
    )

    @Serializable
    data class GroupPersonRoute(
        val persons: List<PersonMovieScreenObject>
    )
}

@Serializable
data object StartRoute : NavRoute

@Serializable
data class CollectionListRoute(
    val category: String? = null,
    val listId: List<String> = listOf()
) : NavRoute

@Serializable
data class MovieListRoute(
    val title: String,
    val queryParameters: List<Pair<String, String>>
) : NavRoute

@Serializable
data class HomeDetailListRoute(
    val title: String,
    val queryParameters: List<Pair<String, String>>
) : NavRoute

@Serializable
data class PersonPodiumListRoute(
    val title: String,
    val queryParameters: List<Pair<String, String>>
) : NavRoute

@Serializable
data class PersonRoute(
    val id: Int
) : NavRoute

@Serializable
data class PersonDetailRoute(
    val id: Int
) : NavRoute

@Serializable
data class AwardListRoute(
    val id: Int,
    val isMovie: Boolean
) : NavRoute


@Serializable
data class OtpRoute(
    val isCreate: Boolean,
    val isDisable: Boolean
) : NavRoute
