package com.example.movieapp.ui.uiState

import com.example.network.module.person.NominationAwardPerson

sealed interface AwardUIState {
    data object Loading: AwardUIState
    data class Success(val data: List<NominationAwardPerson>): AwardUIState
}