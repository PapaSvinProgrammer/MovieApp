package com.mordva.account.presentation.widget.state

internal data class AccountUiState(
    val userAccount: UserAccountState = UserAccountState.Loading
)
