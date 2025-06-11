package com.example.network.module.movie

import kotlinx.serialization.Serializable

@Serializable
data class Fact(
    val value: String,
    val type: String? = null,
    val spoiler: Boolean? = null,
)