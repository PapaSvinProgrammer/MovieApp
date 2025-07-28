package com.example.otpscreen.presentation

import androidx.lifecycle.ViewModel
import com.example.data.external.PreferencesRepository
import com.example.otpscreen.domain.CreateNewCode
import com.example.otpscreen.domain.GetNextFocusedTextFieldIndex
import com.example.otpscreen.domain.GetPreviousFocusedIndex
import com.example.otpscreen.presentation.widget.state.OtpAction
import com.example.otpscreen.presentation.widget.state.UiState
import com.example.security.external.KeyStoreRepository
import com.example.utils.cancelAllJobs
import com.example.utils.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class OtpViewModel @Inject constructor(
    private val getNextFocusedTextFieldIndex: GetNextFocusedTextFieldIndex,
    private val getPreviousFocusedIndex: GetPreviousFocusedIndex,
    private val createNewCode: CreateNewCode,
    private val keyStoreRepository: KeyStoreRepository,
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    private val _defaultCode = MutableStateFlow("")
    val state = _state.asStateFlow()
    val defaultCode = _defaultCode.asStateFlow()

    fun setDefaultPinCode() = launchWithoutOld(SET_PIN_CODE_JOB) {
        preferencesRepository.setAuthorizationState(true)
        val res = keyStoreRepository.encrypt(state.value.code.joinToString(""))

        res.onSuccess {
            _state.value = _state.value.copy(
                isValid = true
            )
        }
    }

    fun getDefaultPinCode() = launchWithoutOld(GET_PIN_CODE_JOB) {
        keyStoreRepository.decrypt().onSuccess {
            _defaultCode.value = it
        }
    }

    fun deleteDefaultPinCode() = launchWithoutOld(DELETE_PIN_CODE_JOB) {
        preferencesRepository.setAuthorizationState(false)
        keyStoreRepository.delete().onSuccess {
            updateValidState(true)
        }
    }

    fun onAction(action: OtpAction) {
        when (action) {
            is OtpAction.OnChangeFieldFocused -> {
                _state.update {
                    it.copy(focusedIndex = action.index)
                }
            }

            is OtpAction.OnEnterNumber -> {
                enterNumber(action.number, action.index)
            }

            OtpAction.OnKeyboardBack -> {
                val previousIndex = getPreviousFocusedIndex.execute(state.value.focusedIndex)

                _state.update {
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
        _state.update {
            it.copy(
                code = (1..DEFAULT_LENGTH).map { null }
            )
        }
    }

    fun updateValidState(state: Boolean?) {
        _state.update {
            it.copy(
                isValid = state
            )
        }
    }

    private fun enterNumber(number: Int?, index: Int) {
        val newCode = createNewCode.execute(
            code = state.value.code,
            number = number,
            index = index
        )

        val wasNumberRemoved = number == null

        _state.update {
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
