package com.mordva.network.internal.model.person

import com.mordva.network.internal.model.movie.ShortMovieDto
import kotlinx.serialization.Serializable

@Serializable
internal data class NominationAwardDto(
    val nomination: NominationDto?,
    val winning: Boolean?,
    val personId: Int?,
    val movie: ShortMovieDto?
)
