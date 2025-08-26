package com.mordva.awardlist.domain.model

import com.mordva.awardlist.presentation.widget.bottomSheet.AwardsFilterType

internal data class RequestParams(
    val id: Int,
    val page: Int,
    val isMovie: Boolean,
    val filterType: AwardsFilterType
)
