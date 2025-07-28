package com.example.otpscreen.presentation.widget.state

internal sealed interface OtpAction {
    data class OnEnterNumber(val number: Int?, val index: Int) : OtpAction
    data class OnChangeFieldFocused(val index: Int): OtpAction
    data object OnKeyboardBack : OtpAction
}