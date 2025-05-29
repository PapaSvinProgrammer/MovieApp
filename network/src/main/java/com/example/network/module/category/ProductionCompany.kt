package com.example.network.module.category

import kotlinx.serialization.Serializable

@Serializable
data class ProductionCompany(
    val name: String?,
    val url: String?,
    val previewUrl: String?,
)