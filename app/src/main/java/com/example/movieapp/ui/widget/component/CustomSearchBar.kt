package com.example.movieapp.ui.widget.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.movieapp.R

@Composable
fun CustomSearchBar(
    query: String,
    onValueChange: (String) -> Unit
) {
    val interaction = remember { MutableInteractionSource() }
    val isFocused = interaction.collectIsFocusedAsState().value

    val focus = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Row {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainerHighest,
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(10.dp)
                .weight(3f)
                .focusRequester(focus)
                .animateContentSize(),
            value = query,
            singleLine = true,
            onValueChange = onValueChange,
            interactionSource = interaction,
            textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onSurface),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
            decorationBox = { innerTextField ->
                if (query.isEmpty()) {
                    Text(stringResource(R.string.input_value))
                }

                innerTextField()
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            )
        )

        AnimatedVisibility(visible = isFocused) {
            TextButton(
                modifier = Modifier.weight(1f),
                onClick = { focusManager.clearFocus() }
            ) {
                Text(
                    text = stringResource(R.string.cancel),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}