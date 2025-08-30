package com.mordva.account.presentation

import androidx.lifecycle.ViewModel
import com.mordva.account.domain.usecase.ClearAllData
import com.mordva.account.domain.usecase.GetUserAccount
import com.mordva.account.presentation.widget.state.AccountUiState
import com.mordva.account.presentation.widget.state.UserAccountState
import com.mordva.util.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class AccountViewModel @Inject constructor(
    private val getUserAccount: GetUserAccount,
    private val clearAllData: ClearAllData,
) : ViewModel() {
    private val _state = MutableStateFlow(AccountUiState())
    val state = _state.asStateFlow()

    init {
        getUserInfo()
    }

    fun clearAllData() = launchWithoutOld(CLEAR_JOB) {
        clearAllData.execute(Unit)
        _state.update { it.copy(isExit = true) }
    }

    private fun getUserInfo() = launchWithoutOld(GET_INFO_JOB) {
        getUserAccount.execute(Unit).onSuccess { user ->
            _state.update {
                it.copy(userAccount = UserAccountState.Success(user))
            }
        }.onFailure { error ->
            _state.update {
                it.copy(userAccount = UserAccountState.Error(error))
            }
        }
    }

    private companion object {
        const val GET_INFO_JOB = "get_user_info"
        const val CLEAR_JOB = "clear_all_data"
    }
}