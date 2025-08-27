package com.mordva.ui.widget.listItems

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.movieapp.ui.R
import com.mordva.ui.theme.Typography

@Composable
fun AwardListItem(
    modifier: Modifier,
    title: String,
    movieName: String?,
    winning: Boolean
) {
    ListItem(
        modifier = modifier,
        headlineContent = {
            Column {
                Text(
                    text = title,
                    fontWeight = FontWeight.Medium,
                    fontSize = Typography.bodyMedium.fontSize
                )

                movieName?.let {
                    Text(
                        text = it,
                        fontWeight = FontWeight.Medium,
                        fontSize = Typography.bodyMedium.fontSize,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Text(
                    text = if (winning)
                        stringResource(R.string.award)
                    else
                        stringResource(R.string.nomination),
                    fontWeight = FontWeight.Medium,
                    fontSize = Typography.bodyMedium.fontSize,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        trailingContent = {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                contentDescription = null
            )
        }
    )
}