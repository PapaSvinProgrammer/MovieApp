package com.mordva.home.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
internal data object HomeRoute

@Serializable
internal data class HomeDetailListRoute(
    val title: String,
    val queryParameters: List<Pair<String, String>>
)