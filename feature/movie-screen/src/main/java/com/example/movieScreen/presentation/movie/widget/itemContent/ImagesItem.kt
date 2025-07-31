package com.example.movieScreen.presentation.movie.widget.itemContent

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.model.image.Poster
import com.example.movieapp.ui.R
import com.example.ui.widget.component.TitleRow
import com.example.ui.widget.lazyComponent.DefaultLazyRow
import com.example.ui.widget.listItems.LastItemCard

internal fun LazyListScope.imagesItem(images: List<Poster>) {
    if (images.isEmpty()) return

    item {
        TitleRow(title = stringResource(R.string.images)) {

        }

        DefaultLazyRow(
            list = images,
            lastItemCard = {
                LastItemCard(
                    width = 200.dp,
                    height = 160.dp
                )
            }
        ) { poster ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(poster.url)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                error = painterResource(R.drawable.ic_image),
                modifier = Modifier
                    .height(160.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }
    }
}