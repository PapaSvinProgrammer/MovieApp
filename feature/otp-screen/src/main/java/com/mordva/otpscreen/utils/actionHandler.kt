package com.mordva.otpscreen.utils

import androidx.compose.ui.focus.FocusRequester
import com.mordva.otpscreen.presentation.OtpViewModel
import com.mordva.otpscreen.presentation.widget.state.OtpAction

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