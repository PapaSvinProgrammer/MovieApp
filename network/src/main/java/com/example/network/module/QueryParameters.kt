package com.example.network.module

data class QueryParameters(
    val page: Int = 1,
    val q: String = "",
    val type: String? = null
)