package com.mordva.network.internal.model.season

import com.mordva.network.internal.model.image.PosterDto
import kotlinx.serialization.Serializable

@Serializable
internal data class EpisodeDto(
    val number: Int?,
    val name: String?,
    val enName: String?,
    val airDate: String?,
    val description: String?,
    val enDescription: String?,
    val still: PosterDto?,
)