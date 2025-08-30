package com.mordva.movieScreen.presentation.movie.widget.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.movieapp.ui.R
import com.mordva.ui.theme.Typography
import com.mordva.util.convert.PrettyData

@Composable
internal fun SeasonDescription(
    modifier: Modifier = Modifier,
    countSeasons: Int,
    countSeries: Int
) {
    val prettySeasons = PrettyData.getPrettyCountSeasons(countSeasons)
    val prettySeries = PrettyData.getPrettyCountSeries(countSeries)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = stringResource(R.string.seasons_series),
                fontSize = Typography.bodyLarge.fontSize,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "$prettySeasons, $prettySeries",
                fontSize = Typography.bodyMedium.fontSize
            )
        }

        Icon(
            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
            contentDescription = null
        )
    }
}