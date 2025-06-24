package com.example.network.model.person

import kotlinx.serialization.Serializable

@Serializable
data class Nomination(
    val award: Award?,
    val title: String?
)