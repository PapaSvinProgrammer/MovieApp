package com.example.awardlist.presentation.widget

import com.example.awardlist.presentation.widget.bottomSheet.AwardsFilterType
import com.example.model.person.NominationAward

internal data class UiState(
    val currentFilterType: AwardsFilterType = AwardsFilterType.BY_DATE,
    val groupAwards: List<Pair<String, List<NominationAward>>> = listOf(),
    val visibleBottomSheet: Boolean = false
)