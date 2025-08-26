package com.mordva.search.domain.model

internal data class RequestParams(
    val selectedIndex: Int = 0,
    val q: String = "",
    val page: Int = 1
)