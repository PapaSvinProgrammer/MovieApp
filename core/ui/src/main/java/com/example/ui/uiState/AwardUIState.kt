package com.example.ui.uiState

import com.example.model.person.NominationAward

sealed interface AwardUIState {
    data object Loading: AwardUIState
    data class Success(val data: List<NominationAward>): AwardUIState
}