package com.mordva.ui.uiState

import androidx.compose.runtime.Immutable
import com.mordva.model.person.NominationAward

@Immutable
sealed interface AwardUIState {
    data object Loading: AwardUIState
    data class Success(val data: List<NominationAward>): AwardUIState
}