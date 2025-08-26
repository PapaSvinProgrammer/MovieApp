package com.mordva.search.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
internal data object SearchRoute

@Serializable
internal data class SearchResultRoute(
    val queryParameters: List<Pair<String, String>>
)

@Serializable
internal data object SearchSettingsRoute