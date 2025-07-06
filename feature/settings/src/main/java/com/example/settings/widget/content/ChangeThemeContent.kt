package com.example.settings.widget.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movieapp.settings.R
import com.example.settings.widget.card.ThemeCard

@Composable
internal fun ChangeThemeContent(
    isDark: Boolean,
    onChangeTheme: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        ThemeCard(
            text = stringResource(R.string.dark),
            icon = painterResource(R.drawable.ic_dark),
            onClick = { onChangeTheme(true) },
            isFocus = isDark
        )

        ThemeCard(
            text = stringResource(R.string.light),
            icon = painterResource(R.drawable.ic_light),
            isFocus = !isDark,
            onClick = { onChangeTheme(false) }
        )
    }
}