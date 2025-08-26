package com.mordva.network.internal.model.totalValue

import kotlinx.serialization.Serializable

@Serializable
internal data class BudgetDto(
    val value: Int?,
    val currency: String?,
)