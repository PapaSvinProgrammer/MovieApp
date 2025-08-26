package com.mordva.ui.widget.listItems

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.movieapp.ui.R
import com.mordva.model.image.CollectionMovie

@Composable
fun CollectionListItem(
    modifier: Modifier = Modifier,
    collectionMovie: CollectionMovie,
    size: Dp = 55.dp,
    leadingIcon: (@Composable () -> Unit)? = null
) {
    ListItem(
        modifier = modifier,
        leadingContent = leadingIcon,
        headlineContent = {
            Row {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(collectionMovie.cover?.url)
                        .crossfade(true)
                        .build(),
                    error = painterResource(R.drawable.ic_image),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(size)
                        .clip(RoundedCornerShape(5.dp))
                )

                Spacer(modifier = Modifier.width(15.dp))

                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = collectionMovie.name.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 2,
                    color = MaterialTheme.colorScheme.onSurface,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    )
}