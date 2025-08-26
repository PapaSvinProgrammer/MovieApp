package com.mordva.movieScreen.presentation.navigation

import com.mordva.movieScreen.domain.model.PersonMovieScreenObject
import com.mordva.movieScreen.domain.model.WatchabilityScreenObject
import kotlinx.serialization.Serializable

@Serializable
internal data class WatchabilityListRoute(
    val watchability: WatchabilityScreenObject
)

@Serializable
internal data class GroupPersonRoute(
    val persons: List<PersonMovieScreenObject>
)