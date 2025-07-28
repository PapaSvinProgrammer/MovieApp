package com.example.settings.presentation.widget.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.settings.presentation.widget.state.ApplicationIcon

@Composable
internal fun IconCard(
    modifier: Modifier = Modifier,
    icon: ApplicationIcon,
    currentIcon: Int,
    currentIndex: Int,
    onClick: () -> Unit
) {
    val tint = if (icon.origColor)
        Color.Unspecified
    else
        MaterialTheme.colorScheme.onSurface

    val borderColor = if (currentIcon - 1 == currentIndex)
        MaterialTheme.colorScheme.primary
    else
        Color.Transparent

    Box(
        modifier = Modifier
            .size(80.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerHighest,
                shape = RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(onClick = onClick)
    ) {
        Icon(
            painter = painterResource(icon.image),
            contentDescription = null,
            tint = tint,
            modifier = modifier.align(Alignment.Center)
        )
    }
}