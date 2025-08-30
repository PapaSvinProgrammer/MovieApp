package com.mordva.movieScreen.presentation.movie.widget.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.movieapp.ui.R
import com.mordva.ui.theme.Typography

@Composable
internal fun WatchabilityDescription(
    modifier: Modifier = Modifier,
    count: Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = stringResource(R.string.where_can_watch),
                fontWeight = FontWeight.Bold,
                fontSize = Typography.bodyLarge.fontSize
            )

            Text(
                text = "Доступно в $count кинотеатрах",
                fontSize = Typography.bodyMedium.fontSize,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Icon(
            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
            contentDescription = null
        )
    }
}