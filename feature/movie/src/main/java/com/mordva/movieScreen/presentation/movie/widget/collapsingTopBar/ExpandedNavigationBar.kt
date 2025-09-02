package com.mordva.movieScreen.presentation.movie.widget.collapsingTopBar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.ui.R

@Composable
internal fun ExpandedNavigationBar(
    customRating: Int? = null,
    onEvaluate: () -> Unit,
    onAddIntoFuturePackage: () -> Unit,
    onShare: () -> Unit,
    onMore: () -> Unit
) {
    Row {
        NavigationTab(
            painter = if (customRating != null)
                painterResource(R.drawable.ic_star_fill)
            else
                painterResource(R.drawable.ic_star),
            title = stringResource(R.string.evaluate),
            clickable = onEvaluate,
            color = if (customRating != null)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.onSurfaceVariant
        )

        NavigationTab(
            painter = painterResource(R.drawable.ic_bookmark_add),
            title = stringResource(R.string.will_watching),
            clickable = onAddIntoFuturePackage
        )

        NavigationTab(
            painter = painterResource(R.drawable.ic_share),
            title = stringResource(R.string.share),
            clickable = onShare
        )

        NavigationTab(
            painter = painterResource(R.drawable.ic_more_horiz),
            title = stringResource(R.string.more),
            clickable = onMore
        )
    }
}

@Composable
private fun NavigationTab(
    modifier: Modifier = Modifier,
    painter: Painter,
    title: String,
    color: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    clickable: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .height(45.dp)
            .width(90.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = clickable
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = modifier,
            painter = painter,
            contentDescription = null,
            tint = color
        )

        Text(
            text = title,
            fontSize = 10.sp
        )
    }
}