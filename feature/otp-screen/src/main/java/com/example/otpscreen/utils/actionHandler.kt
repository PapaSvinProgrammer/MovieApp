package com.example.otpscreen.utils

import androidx.compose.ui.focus.FocusRequester
import com.example.otpscreen.presentation.OtpViewModel
import com.example.otpscreen.presentation.widget.state.OtpAction

internal fun actionHandler(
    action: OtpAction,
    viewModel: OtpViewModel,
    focusRequesters: List<FocusRequester>
) {
    when (action) {
        is OtpAction.OnEnterNumber -> {
            if (action.number != null) {
                focusRequesters[action.index].freeFocus()
            }
        }

        else -> Unit
    }

    viewModel.onAction(action)
}