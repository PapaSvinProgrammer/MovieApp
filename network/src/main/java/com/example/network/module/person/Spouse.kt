package com.example.network.module.person

import kotlinx.serialization.Serializable

@Serializable
data class Spouse(
    val id: Int,
    val name: String? = null,
    val children: Int? = null,
    val divorced: Boolean? = null,
    val relation: String? = null
)