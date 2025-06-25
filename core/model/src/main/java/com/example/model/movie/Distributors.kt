package com.example.network.model.movie

import kotlinx.serialization.Serializable

@Serializable
data class Distributors(
    val distributor: String?,
    val distributorRelease: String?,
)
