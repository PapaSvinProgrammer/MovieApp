package com.mordva.settings.presentation.widget.content

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.ui.R
import com.mordva.settings.presentation.widget.card.ThemeCard

@Composable
internal fun ChangeThemeContent(
    themeState: Int,
    onChangeTheme: (Int) -> Unit
) {
    Text(
        text = stringResource(R.string.theme),
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp
    )
    Spacer(modifier = Modifier.height(15.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        ThemeCard(
            text = stringResource(R.string.system_theme),
            icon = painterResource(R.drawable.ic_brightness),
            width = 130.dp,
            onClick = { onChangeTheme(1) },
            isFocus = themeState == 1
        )

        ThemeCard(
            text = stringResource(R.string.dark),
            icon = painterResource(R.drawable.ic_dark),
            onClick = { onChangeTheme(2) },
            isFocus = themeState == 2
        )

        ThemeCard(
            text = stringResource(R.string.light),
            icon = painterResource(R.drawable.ic_light),
            isFocus = themeState == 3,
            onClick = { onChangeTheme(3) }
        )
    }
}
