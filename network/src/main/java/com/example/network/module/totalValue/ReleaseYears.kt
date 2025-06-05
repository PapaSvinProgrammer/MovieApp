package com.example.network.module.totalValue

import kotlinx.serialization.Serializable

@Serializable
data class ReleaseYears(
    val start: Int? = null,
    val end: Int? = null
)