package com.mordva.network.internal.model.person

import kotlinx.serialization.Serializable

@Serializable
internal data class NominationDto(
    val award: AwardDto?,
    val title: String?
)