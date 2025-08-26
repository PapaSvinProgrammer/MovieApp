package com.mordva.domain.usecase.person.model

data class PersonParams(
    val personId: Int = -1,
    val q: String = "",
    val page: Int = 1
)
