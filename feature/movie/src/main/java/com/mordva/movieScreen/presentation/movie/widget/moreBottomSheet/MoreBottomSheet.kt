package com.mordva.movieScreen.presentation.movie.widget.moreBottomSheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.movieapp.movie.R
import com.mordva.model.movie.Movie
import com.mordva.movieScreen.presentation.movie.widget.scoreBottomSheet.DisableChangeStatusBarIconColor
import com.mordva.ui.theme.Typography
import com.mordva.util.convert.ConvertData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MoreBottomSheet(
    movie: Movie,
    isBlocked: Boolean,
    isVisibility: Boolean,
    onDismissRequest: () -> Unit,
    onAction: (MoreSheetAction) -> Unit
) {
    ModalBottomSheet(onDismissRequest = onDismissRequest) {
        TopSheetContent(movie)
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalDivider()

        ListItem(
            modifier = Modifier.clickable {
                onAction(MoreSheetAction.AddInFolder)
                onDismissRequest()
            },
            colors = ListItemDefaults.colors(MaterialTheme.colorScheme.surfaceContainer),
            leadingContent = {
                Icon(
                    painter = painterResource(R.drawable.ic_new_folder),
                    contentDescription = null
                )
            },
            headlineContent = {
                Text(
                    text = "Добавить в папку",
                    fontSize = Typography.bodyMedium.fontSize
                )
            }
        )

        HorizontalDivider(modifier = Modifier.padding(start = 15.dp))

        ListItem(
            modifier = Modifier.clickable {
                onAction(MoreSheetAction.BlockedChange)
                onDismissRequest()
            },
            colors = ListItemDefaults.colors(MaterialTheme.colorScheme.surfaceContainer),
            leadingContent = {
                Icon(
                    painter = if (isBlocked)
                        painterResource(R.drawable.ic_stop_fill)
                    else
                        painterResource(R.drawable.ic_stop),
                    tint = if (isBlocked)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.surface,
                    contentDescription = null
                )
            },
            headlineContent = {
                Text(
                    text = "Неинтересно",
                    fontSize = Typography.bodyMedium.fontSize
                )
            }
        )

        HorizontalDivider(modifier = Modifier.padding(start = 15.dp))

        ListItem(
            modifier = Modifier.clickable {
                onAction(MoreSheetAction.VisibilityChange)
                onDismissRequest()
            },
            colors = ListItemDefaults.colors(MaterialTheme.colorScheme.surfaceContainer),
            leadingContent = {
                Icon(
                    painter = if (isVisibility)
                        painterResource(R.drawable.ic_visibility_fill)
                    else
                        painterResource(R.drawable.ic_visibility),
                    tint = if (isBlocked)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.surface,
                    contentDescription = null
                )
            },
            headlineContent = {
                Text(
                    text = "Просмотрен",
                    fontSize = Typography.bodyMedium.fontSize
                )
            }
        )

        DisableChangeStatusBarIconColor()
    }
}

@Composable
private fun TopSheetContent(movie: Movie) {
    Row(
        modifier = Modifier.padding(start = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = movie.poster?.url,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(130.dp)
                .width(90.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Spacer(modifier = Modifier.width(20.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = movie.name ?: "",
                fontSize = Typography.bodyMedium.fontSize,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = ConvertData.convertDateCreated(
                    year = movie.year,
                    releaseYears = movie.releaseYears
                ),
                fontSize = Typography.bodyMedium.fontSize,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}