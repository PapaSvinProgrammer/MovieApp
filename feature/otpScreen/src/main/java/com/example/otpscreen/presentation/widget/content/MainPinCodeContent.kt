package com.example.otpscreen.presentation.widget.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.unit.dp
import com.example.otpscreen.presentation.widget.OtpInputField
import com.example.otpscreen.presentation.widget.state.OtpAction
import com.example.otpscreen.presentation.widget.state.UiState

@Composable
internal fun MainPinCodeContent(
    state: UiState,
    focusRequesters: List<FocusRequester>,
    onAction: (OtpAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        state.code.forEachIndexed { index, number ->
            OtpInputField(
                number = number,
                focusRequester = focusRequesters[index],
                onFocusChanged = { isFocused ->
                    if (isFocused) {
                        onAction(OtpAction.OnChangeFieldFocused(index))
                    }
                },
                onNumberChanged = { newNumber ->
                    onAction(OtpAction.OnEnterNumber(newNumber, index))
                },
                onKeyboardBack = {
                    onAction(OtpAction.OnKeyboardBack)
                }
            )
        }
    }
}
