package com.example.movieScreen.widget.collapsingTopBar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.ui.R

@Composable
fun ExpandedNavigationBar() {
    Row {
        NavigationTab(
            painter = painterResource(R.drawable.ic_star),
            title = stringResource(R.string.evaluate)
        )

        NavigationTab(
            painter = painterResource(R.drawable.ic_bookmark_add),
            title = stringResource(R.string.will_watching)
        )

        NavigationTab(
            painter = painterResource(R.drawable.ic_share),
            title = stringResource(R.string.share)
        )

        NavigationTab(
            painter = painterResource(R.drawable.ic_more_horiz),
            title = stringResource(R.string.more)
        )
    }
}

@Composable
private fun NavigationTab(
    modifier: Modifier = Modifier,
    painter: Painter,
    title: String
) {
    Column(
        modifier = Modifier
            .height(45.dp)
            .width(90.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = modifier,
            painter = painter,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = title,
            fontSize = 10.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}