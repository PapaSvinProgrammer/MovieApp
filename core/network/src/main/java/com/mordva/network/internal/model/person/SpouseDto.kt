package com.mordva.network.internal.model.person

import kotlinx.serialization.Serializable

@Serializable
internal data class SpouseDto(
    val id: Int,
    val name: String? = null,
    val children: Int? = null,
    val divorced: Boolean? = null,
    val relation: String? = null
)