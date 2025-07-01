package com.example.network.internal.model.person

import kotlinx.serialization.Serializable

@Serializable
internal data class NominationDto(
    val awardDto: AwardDto?,
    val title: String?
)