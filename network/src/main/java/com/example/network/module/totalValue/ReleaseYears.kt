package com.example.network.module.totalValue

import kotlinx.serialization.Serializable

@Serializable
data class ReleaseYears(
    val start: Int?,
    val end: Int?
)