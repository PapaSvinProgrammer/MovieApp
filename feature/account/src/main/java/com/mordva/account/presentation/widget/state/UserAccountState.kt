package com.mordva.account.presentation.widget.state

import com.mordva.account.domain.model.UserAccount

internal sealed interface UserAccountState {
    data class Success(val data: UserAccount) : UserAccountState
    data object Loading : UserAccountState
    data class Error(val error: Throwable) : UserAccountState
}