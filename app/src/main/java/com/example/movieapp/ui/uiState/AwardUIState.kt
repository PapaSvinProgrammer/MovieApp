package com.example.movieapp.ui.uiState

import com.example.network.module.person.NominationAward

sealed interface AwardUIState {
    data object Loading: AwardUIState
    data class Success(val data: List<NominationAward>): AwardUIState
}