package com.mordva.navigation

import kotlinx.serialization.Serializable

/**
 * Здесь прописываем имена глобальных графов
 */

sealed interface RootGraph

@Serializable
data object HomeGraph : RootGraph

@Serializable
data object FavoriteGraph : RootGraph

@Serializable
data object SearchGraph : RootGraph

@Serializable
data object AccountGraph : RootGraph

@Serializable
data object AboutAppGraph : RootGraph

@Serializable
data object AwardListGraph : RootGraph {
    @Serializable
    data class AwardsListRoute(
        val id: Int,
        val isMovie: Boolean
    )
}

@Serializable
data object CollectionListGraph : RootGraph {
    @Serializable
    data class CollectionListRoute(
        val category: String? = null,
        val listId: List<String> = listOf()
    )
}

@Serializable
data object MovieListGraph : RootGraph {
    @Serializable
    data class MovieListRoute(
        val title: String,
        val queryParameters: List<Pair<String, String>>
    )
}

@Serializable
data object MovieGraph : RootGraph {
    @Serializable
    data class MovieRoute(
        val id: Int
    )
}

@Serializable
data object OtpGraph : RootGraph {
    @Serializable
    data class OtpRoute(
        val isCreate: Boolean,
        val isDisable: Boolean
    )
}

@Serializable
data object PersonPodiumListGraph : RootGraph {
    @Serializable
    data class PersonPodiumListRoute(
        val title: String,
        val queryParameters: List<Pair<String, String>>
    )
}

@Serializable
data object PersonGraph : RootGraph {
    @Serializable
    data class PersonRoute(
        val id: Int
    )
}

@Serializable
data object SettingsGraph : RootGraph {
    @Serializable
    data object SoundRoute

    @Serializable
    data object ConfidentialRoute

    @Serializable
    data object DataMemoryRoute

    @Serializable
    data object LanguageRoute

    @Serializable
    data object DecorRoute

    @Serializable
    data object SupportRoute
}