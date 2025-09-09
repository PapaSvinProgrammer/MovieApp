package com.mordva.movieScreen.utils

import android.content.Context
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import com.example.movieapp.ui.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal fun SnackbarHostState.handleSnackBarSate(
    scope: CoroutineScope,
    isChecked: Boolean,
    context: Context
) {
    val text = if (isChecked)
        context.getString(R.string.delete_from_will_watch)
    else
        context.getString(R.string.add_in_will_watch)

    scope.launch {
        this@handleSnackBarSate.showSnackbar(
            message = text,
            duration = SnackbarDuration.Short
        )
    }
}