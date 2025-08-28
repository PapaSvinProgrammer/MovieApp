package com.mordva.otpscreen.presentation

import androidx.lifecycle.ViewModel
import com.mordva.domain.repository.PreferencesRepository
import com.mordva.otpscreen.domain.CreateNewCode
import com.mordva.otpscreen.domain.GetNextFocusedTextFieldIndex
import com.mordva.otpscreen.domain.GetPreviousFocusedIndex
import com.mordva.otpscreen.presentation.widget.state.OtpAction
import com.mordva.otpscreen.presentation.widget.state.UiState
import com.mordva.security.external.SecurityRepository
import com.mordva.security.internal.util.SecurityType
import com.mordva.util.cancelAllJobs
import com.mordva.util.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class OtpViewModel @Inject constructor(
    private val getNextFocusedTextFieldIndex: GetNextFocusedTextFieldIndex,
    private val getPreviousFocusedIndex: GetPreviousFocusedIndex,
    private val createNewCode: CreateNewCode,
    private val keyStoreRepository: SecurityRepository,
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun setDefaultPinCode() = launchWithoutOld(SET_PIN_CODE_JOB) {
        preferencesRepository.setAuthorizationState(true)
        val res = keyStoreRepository.setData(
            data = uiState.value.code.joinToString(""),
            type = SecurityType.PASSWORD
        )

        res.onSuccess {
            _uiState.value = _uiState.value.copy(
                isValid = true
            )
        }
    }

    fun getDefaultPinCode() = launchWithoutOld(GET_PIN_CODE_JOB) {
        keyStoreRepository.getData(SecurityType.PASSWORD).onSuccess { pinCode ->
            _uiState.update {
                it.copy(defaultCode = pinCode)
            }
        }
    }

    fun deleteDefaultPinCode() = launchWithoutOld(DELETE_PIN_CODE_JOB) {
        preferencesRepository.setAuthorizationState(false)
        keyStoreRepository.deleteData(SecurityType.PASSWORD)
        updateValidState(true)
    }

    fun onAction(action: OtpAction) {
        when (action) {
            is OtpAction.OnChangeFieldFocused -> {
                _uiState.update {
                    it.copy(focusedIndex = action.index)
                }
            }

            is OtpAction.OnEnterNumber -> {
                enterNumber(action.number, action.index)
            }

            OtpAction.OnKeyboardBack -> {
                val previousIndex = getPreviousFocusedIndex.execute(uiState.value.focusedIndex)

                _uiState.update {
                    it.copy(
                        code = it.code.mapIndexed { index, number ->
                            if (index == previousIndex) {
                                null
                            } else {
                                number
                            }
                        },
                        focusedIndex = previousIndex
                    )
                }
            }
        }
    }

    fun resetCode() {
        _uiState.update {
            it.copy(
                code = (1..DEFAULT_LENGTH).map { null }
            )
        }
    }

    fun updateValidState(state: Boolean?) {
        _uiState.update {
            it.copy(
                isValid = state
            )
        }
    }

    private fun enterNumber(number: Int?, index: Int) {
        val newCode = createNewCode.execute(
            code = uiState.value.code,
            number = number,
            index = index
        )

        val wasNumberRemoved = number == null

        _uiState.update {
            it.copy(
                code = newCode,
                focusedIndex = if (wasNumberRemoved || it.code.getOrNull(index) != null) {
                    it.focusedIndex
                } else {
                    getNextFocusedTextFieldIndex.execute(
                        currentCode = it.code,
                        currentFocusedIndex = it.focusedIndex
                    )
                }
            )
        }
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    companion object {
        private const val SET_PIN_CODE_JOB = "set_default_pin_code"
        private const val GET_PIN_CODE_JOB = "get_default_pin_code"
        private const val DELETE_PIN_CODE_JOB = "delete_pin_code"
        const val DEFAULT_LENGTH = 4
    }
}
